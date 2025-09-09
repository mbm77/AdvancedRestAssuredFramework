package java8.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**** Reverse a String *****/
class ReverseAString {
	public void reverseAString() {

		String str = "Java";
		String revStr = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			revStr = revStr + str.charAt(i);
		}
		System.out.println(revStr);
	}

}

/****** Count the Number of Vowels *******/

class CountNumberOfVowels {
	public void countNumberOfVowels() {

		String str = "Hello World";
		int count = 0;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				count++;
			}
		}

		System.out.println("Total Vowel is String are : " + count);
	}

}

/********* Convert String to Title Case *********/

class ConvertStringToTitleCase {
	public void convertStringToTitleCase() {

		String str = "java is fun";
		String[] strArr = str.split(" ");
		String titleCase = "";
		String newStr = "";
		for (int i = 0; i < strArr.length; i++) {
			newStr = strArr[i].substring(0, 1).toUpperCase() + strArr[i].substring(1) + " ";

			titleCase = titleCase + newStr;
		}

		System.out.println(titleCase.trim());
	}

}

/******* Check if a String is a Palindrome *********/

class CheckingStringPalindrome {
	public void checkingStringPalindrome() {

		String str = "madam";
		boolean isPalindrome = false;
		String revStr = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			revStr = revStr + str.charAt(i);
		}

		System.out.println(revStr);
		if (str.equalsIgnoreCase(revStr)) {
			isPalindrome = true;
			System.out.println(isPalindrome);
		} else {
			System.out.println(isPalindrome);
		}

	}

}

///****************Longest Word in a Sentence**********
class LongestWordInSentence {
	public void longestWordInSentence() {

		String str = "Java is a powerful language";
		String[] strArr = str.split(" ");
		String maxStr = "";
		int max = 0;
		for (int i = 0; i < strArr.length; i++) {
			if (max < strArr[i].length()) {
				max = strArr[i].length();
				maxStr = strArr[i];
			}
		}

		System.out.println(maxStr);
	}

}

/************ Check if Two Strings are Anagrams ***********/
class CheckStringsAnagrams {
	public void checkStringsAnagrams() {

		String str1 = "listen";
		String str2 = "silent";
		boolean flag = false;
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		Arrays.sort(charArr1);
		Arrays.sort(charArr2);
		if (Arrays.equals(charArr1, charArr2)) {
			flag = true;
		} else {
			flag = false;
		}

		if (flag == true) {
			System.out.println("Given two strings are anagrams");
		} else {
			System.out.println("Given two strings not are anagrams");
		}
	}

}

/**************** Remove Duplicate Characters from a String ***********/
class RemoveDuplicateCharsFromString {
	public void removeDuplicateCharsFromString() {

		String str = "programming";
		char ch;
		List<Character> list = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (!list.contains(ch)) {
				list.add(ch);
			}
		}
		for (Character c : list) {
			System.out.print(c);
		}
	}

}

/********* Find the First Non-Repeating Character *******/
class FindFirstNonRepeatingCharacter {
	static char firstNonRepeat;

	public void main(String[] args) {

		String str = "swiss";
		char ch;

		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			int count = 0;
			for (int j = 0; j < str.length(); j++) {
				if (ch == str.charAt(j)) {
					count++;
				}
			}
			if (count == 1) {
				firstNonRepeat = ch;
				break;
			}
		}
		System.out.print(firstNonRepeat);
	}

}

/******* to Get the Most Frequent Character ******/
class GetMostFrequentCharacter {

	public void getMostFrequentCharacter() {

		String str = "hello";
		Map<Character, Integer> map = new HashMap<>();
		char[] charArr = str.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			if (map.containsKey(charArr[i])) {
				map.put(charArr[i], map.get(charArr[i]) + 1);
			} else {
				map.put(charArr[i], 1);
			}

		}
		char maxChar = ' ';
		int maxCount = 0;
		for (Entry<Character, Integer> entry : map.entrySet()) {
			if (maxCount < entry.getValue()) {
				maxCount = entry.getValue();
				maxChar = entry.getKey();

			}
		}

		System.out.println(maxChar);
	}

}

/**************** compresses a string ************************/
class CompressAString {
	public void compressString(String[] args) {
		String str = "aaabbcddd";
		Map<Character, Integer> map = new HashMap<>();
		char[] charArr = str.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			if (map.containsKey(charArr[i])) {
				map.put(charArr[i], map.get(charArr[i]) + 1);
			} else {
				map.put(charArr[i], 1);
			}
		}
		/*
		 * Set<Character> keys = map.keySet(); for(Character k : keys) {
		 * System.out.print(k+""+map.get(k)); }
		 */
		for (Entry<Character, Integer> entry : map.entrySet()) {
			System.out.print(entry.getKey() + "" + entry.getValue());
		}
	}
}

/******** possible substrings ****************/
class PossibleSubstrings {

	public void findPossibleSubstrings(String[] args) {
		String str = "abc";
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j <= str.length(); j++) {
				System.out.println(str.substring(i, j));
			}
		}
	}

}

class RandomString {
	public void generateRandomString() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			sb.append(str.charAt(random.nextInt(str.length())));
		}
		System.out.println(sb.toString());
	}
}

public class StringFunctionPractice {
	 public static void displaySubstr() {
        String str = "abcdefgh";
        for(int i=0;i<str.length();i++){
            for(int j=i+1;j<=str.length();j++){
             String subStr = str.substring(i,j);
              System.out.println(subStr);
            }
        }
    
    }
	public static void main(String[] args) {
displaySubstr();
	}
}

