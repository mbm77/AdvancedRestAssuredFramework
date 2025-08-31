package java8.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Employee class
class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}

// Main class
public class ListToMapEmp {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(101, "king", 50000.00));
        empList.add(new Employee(102, "mbm", 60000.00));
        empList.add(new Employee(103, "java", 70000.00));
        empList.add(new Employee(104, "hero", 55000.00));

        // Convert List<Employee> to Map<Integer, String>
        Map<Integer, String> empMap = empList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getName));

        System.out.println("Employee Map (id -> name): " + empMap);
    }
}
