package com.mbm.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static Map<String, Object> getJsonDataAsMap(String endpoinstJsonFile)
			throws StreamReadException, DatabindException, IOException {
		String completeJsonFilePath = endpoinstJsonFile;
		Map<String, Object> data = objectMapper.readValue(new File(completeJsonFilePath), new TypeReference<>() {
		});
		return data;

	}
}
