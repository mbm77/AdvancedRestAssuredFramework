package java8.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMapEmp {
	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(101, "king", 50000.00));
		empList.add(new Employee(102, "mbm", 60000.00));
		empList.add(new Employee(103, "java", 70000.00));
		empList.add(new Employee(104, "hero", 55000.00));
		
		Map<Integer,String> empMap = empList.stream().collect(Collectors.toMap(emp->emp.getId(), emp->emp.getName()));
		System.out.println(empMap);

	}

}

