package java8.filter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetHighestSalaryOfDepartments {
	public static void main(String[] args) {
		List<Employees> employees = new ArrayList<>();
		employees.add(new Employees("mbm", "IT", 50000.00));
		employees.add(new Employees("sing", "IT", 60000.00));
		employees.add(new Employees("ming", "HR", 70000.00));
		employees.add(new Employees("wing", "HR", 80000.00));
		employees.add(new Employees("piang", "FINANCE", 40000.00));
		employees.add(new Employees("giang", "FINANCE", 45000.00));

		Map<String, List<Employees>> departments = employees.stream()
				.collect(Collectors.groupingBy(emp -> emp.getDepartment()));

		Map<String, Employees> departmentWithHighsalary = departments.entrySet().stream().collect(Collectors.toMap(
				entry -> entry.getKey(),
				entry -> entry.getValue().stream().max(Comparator.comparing((Employees::getSalary))).orElse(null)));

		for (Map.Entry<String, Employees> entry : departmentWithHighsalary.entrySet()) {
			System.out.println("Department " + entry.getKey() + "Highest Salary " + entry.getValue().getSalary());
		}

	}

}

class Employees {
	private String name;
	private String department;
	private double salary;

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}

	public Employees(String name, String department, double salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}
}
