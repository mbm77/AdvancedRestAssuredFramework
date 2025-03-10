package java8.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AboveAvgEmp {
	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(101, "mvm", 50000.00));
		empList.add(new Employee(102, "hgh", 60000.00));
		empList.add(new Employee(103, "fdg", 70000.00));
		empList.add(new Employee(104, "yyt", 52000.00));
		
		double avgSalary = empList.stream().collect(Collectors.averagingDouble(emp -> emp.getSalary()));

		List<Employee> aboveAvgEmp = empList.stream().filter(map -> map.getSalary() > avgSalary)
				.collect(Collectors.toList());
		for (Employee emp : aboveAvgEmp) {
			System.out.println(emp.getName());
			System.out.println(emp.getName());
			
			
		}
	}
}

class Employee {
	private String name;
	private int id;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	private double salary;

	Employee(int id, String name, double salary) {
		this.name = name;
		this.salary = salary;
		this.id = id;
	}

}
