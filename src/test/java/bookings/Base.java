package bookings;

import java.io.IOException;
import java.util.Map;

import utils.JsonUtils;

public class Base {
	
	public static Map<String,Object> dataFromJsonFile;
	
	static {
				//test -Denv=qa
				String env = System.getProperty("env") == null ? "qa" : "dev";
				try {
					dataFromJsonFile = JsonUtils.getJsonDataAsMap("bookins/"+env+"/bookingAPIData.json");
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

}
