package java8.filter;

public class StackOverflowExample {
	
	public static void recursiveMethod() {
		System.out.println("resting");
		recursiveMethod();
	}
	
	public static void main(String[] args) {
		recursiveMethod();
		
	}

}
