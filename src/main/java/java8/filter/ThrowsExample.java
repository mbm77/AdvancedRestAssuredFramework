package java8.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ThrowsExample {
	public static void readFile() {
		try (FileReader file = new FileReader(
				System.getProperty("user.dir") + "/src/test/resources/test-data/tes.txt");) {
			BufferedReader br = new BufferedReader(file);
			String line = "";
			// (line = br.readLine()
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		readFile();
	}
}
