package payload.order;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadFromJsonArray {
	public static void main(String[] args) {
		File file = new File("src/main/resources/employee.json");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode rootNode = objectMapper.readTree(file);
			JsonNode membersArray = rootNode.get("members");
			for (JsonNode member : membersArray) {
				System.out.println(member.toPrettyString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
