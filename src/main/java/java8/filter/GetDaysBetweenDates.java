package java8.filter;

public class GetDaysBetweenDates {
	public static void main(String[] args) {
		String date1 = "08/03/2025";
		String date2 = "12/03/2025";

		String[] dateArr1 = date1.split("/");
		String[] dateArr2 = date2.split("/");

		int[] intDate1 = parseDate(dateArr1);
		int[] intDate2 = parseDate(dateArr2);

		int days1 = findNumberDays(intDate1[0], intDate1[1], intDate1[2]);
		int days2 = findNumberDays(intDate2[0], intDate2[1], intDate2[2]);

		int difference = Math.abs(days2 - days1);
		
		System.out.println(difference);

	}

	public static int findNumberDays(int day, int month, int year) {
		int days = 0;
		for (int i = 1; i < year; i++) {
			days = days + (isLeapYear(i) ? 366 : 365);
		}

		int[] monthDays = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		monthDays[2] = (isLeapYear(year) ? 29 : 28);
		for (int i = 1; i < month; i++) {
			days = days + monthDays[i];
		}

		return days + day;

	}

	public static int[] parseDate(String[] date1) {
		return new int[] { Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]) };
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year != 100) || (year % 400 == 0);
	}

}
