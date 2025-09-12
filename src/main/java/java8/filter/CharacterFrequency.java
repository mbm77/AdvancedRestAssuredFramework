package java8.filter;

public class CharacterFrequency {
	public static void main(String[] args) {
		
		String str = "Selenium Java";
		str.toLowerCase().chars().filter(ch->ch!=' ');
		
	}

}
