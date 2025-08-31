package java8.filter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//Online Java Compiler
//Use this editor to write, compile and run your Java code online
import java.util.function.Supplier;
class LocalDatePractice {
 public static void main(String[] args) {
     Supplier<String> randomNameSupplier = () -> {
       //  String[] names = {"Alice", "Bob", "Charlie", "Diana"};
        // return names[new Random().nextInt(names.length)];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String todayDate = LocalDate.now().format(formatter);
        LocalDate date = LocalDate.parse(todayDate, formatter);
        LocalDate newDate  = date.plusDays(7);
        return date.format(formatter)+" "+newDate.format(formatter);
     };
    System.out.println(randomNameSupplier.get());
 }
}
