package com.mbm.constants;

public class FrameworkConstants {
	private FrameworkConstants() {
	}

	private static final String RESOURCEPATH = System.getProperty("user.dir") + "/src/test/resources/";
	private static final String QAENDPOINTS = RESOURCEPATH + "bookings/qa/bookingAPIData.json";
	private static final String DEVENDPOINTS = RESOURCEPATH + "bookings/dev/bookingAPIData.json";
	private static final String CONFIGFILEPATH = RESOURCEPATH + "config/config.properties";

	public static String getResourcePath() {
		return RESOURCEPATH;
	}

	public static String getQAEndpoints() {
		return QAENDPOINTS;
	}

	public static String getDevEndpoints() {
		return DEVENDPOINTS;
	}

	public static String getConfigFilePath() {
		return CONFIGFILEPATH;
	}

}
