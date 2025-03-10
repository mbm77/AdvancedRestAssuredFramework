package com.mbm.bookingtests;

import java.io.IOException;
import java.util.Map;

import com.mbm.constants.FrameworkConstants;
import com.mbm.utils.JsonUtils;

public class Base {

	public static Map<String, Object> dataFromJsonFile;

	static {
		// test -Denv=qa
		// String env = System.getProperty("env") == null ? "qa" : "dev";
		// String env = System.getProperty("env","qa");
		if (System.getProperty("env", "qa") == "qa") {
			try {
				dataFromJsonFile = JsonUtils.getJsonDataAsMap(FrameworkConstants.getQAEndpoints());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (System.getProperty("env") == "dev") {
			try {
				dataFromJsonFile = JsonUtils.getJsonDataAsMap(FrameworkConstants.getDevEndpoints());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
