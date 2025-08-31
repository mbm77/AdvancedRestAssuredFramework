package java8.filter;
import java.util.*;

public class GetDaysBetweenDates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter start date (dd/MM/yyyy):");
        String date1 = scanner.nextLine();
        System.out.println("enter end date (dd/MM/yyyy):");
        String date2 = scanner.nextLine();

        String[] dateArr1 = date1.split("/");
        String[] dateArr2 = date2.split("/");

        int[] intDate1 = parseDate(dateArr1);
        int[] intDate2 = parseDate(dateArr2);

        int days1 = findNumberDays(intDate1[0], intDate1[1], intDate1[2]);
        int days2 = findNumberDays(intDate2[0], intDate2[1], intDate2[2]);

        int difference = Math.abs(days2 - days1);
        System.out.println("Difference in days: " + difference);

        scanner.close();
    }

    public static int findNumberDays(int day, int month, int year) {
        int days = 0;

        // Add days for all previous years
        for (int i = 1; i < year; i++) {
            days += (isLeapYear(i) ? 366 : 365);
        }

        // Add days for all previous months in the same year
        int[] monthDays = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        monthDays[2] = (isLeapYear(year) ? 29 : 28);

        for (int i = 1; i < month; i++) {
            days += monthDays[i];
        }

        return days + day;
    }

    public static int[] parseDate(String[] date) {
        return new int[] { Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]) };
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
