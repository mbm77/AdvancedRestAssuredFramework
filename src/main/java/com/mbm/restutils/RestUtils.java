package com.mbm.restutils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.mbm.bookingpojos.Booking;
import com.mbm.bookingpojos.BookingTestData;
import com.mbm.bookingpojos.CreateBooking;
import com.mbm.bookingpojos.TokenCredentials;
import com.mbm.filelogging.FileLogger;
import com.mbm.reporting.ExtentLogger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class RestUtils {

	private static RequestSpecification getRequestSpecification(String baseUrl, String basePath, Integer bookingId,
			String accessToken, Object requestPayload, Map<String, String> headers) {

		RequestSpecification requestSpec = RestAssured.given().baseUri(baseUrl).basePath(basePath)
				.contentType(ContentType.JSON);

		if (bookingId != null) {
			requestSpec.pathParam("bookingId", bookingId);
		}

		requestSpec.headers(prepareHeaders(headers, accessToken));

		if (requestPayload != null) {
			requestSpec.body(requestPayload);
		}

		return requestSpec;
	}

	private static Map<String, String> prepareHeaders(Map<String, String> headers, String accessToken) {
		if (headers == null) {
			headers = new HashMap<>();
		}
		if (accessToken != null && !accessToken.isEmpty()) {
			headers.put("Cookie", "token=" + accessToken);
		}
		return headers;

	}

	private static void logRequestInReport(RequestSpecification requestSpecification, Integer bookingId) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);

		String endpoint = queryableRequestSpecification.getBaseUri();
		if (bookingId != null) {
			endpoint = endpoint + bookingId;
		}
		ExtentLogger.logInfoDetails("Endpoint is " + endpoint);
		FileLogger.logInfoDetails("Endpoint is " + endpoint);
		
		String method = queryableRequestSpecification.getMethod();
		ExtentLogger.logInfoDetails("Method is " + method);
		FileLogger.logInfoDetails("Method is " + method);
		
		ExtentLogger.logInfoDetails("Request Headers are ");
		FileLogger.logInfoDetails("Request Headers are ");
		
		ExtentLogger.logHeaders(queryableRequestSpecification.getHeaders().asList());
		FileLogger.logHeaders(queryableRequestSpecification.getHeaders().asList());

		if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
			Object requestBody = queryableRequestSpecification.getBody();
			String bodyContent = (requestBody != null) ? requestBody.toString() : "No request body";
			ExtentLogger.logInfoDetails("Request Body is");
			FileLogger.logInfoDetails("Request Body is");
			
			ExtentLogger.logJson(bodyContent);
			FileLogger.logJson(bodyContent);
		} else {
			ExtentLogger.logInfoDetails("No Request Body for " + method + " Method");
			FileLogger.logInfoDetails("No Request Body for " + method + " Method");
		}
	}

	private static void logResponseInReport(Response response) {
		ExtentLogger.logInfoDetails("Response status is " + response.getStatusLine());
		FileLogger.logInfoDetails("Response status is " + response.getStatusLine());
		
		ExtentLogger.logInfoDetails("Response Headers are ");
		FileLogger.logInfoDetails("Response Headers are ");
		
		ExtentLogger.logHeaders(response.getHeaders().asList());
		FileLogger.logHeaders(response.getHeaders().asList());
		
		String responseBody = response.getBody() != null ? response.getBody().asString().trim() : "";

		if (!responseBody.isEmpty()) {
			ExtentLogger.logInfoDetails("Response Body is ");
			FileLogger.logInfoDetails("Response Body is ");
			if (responseBody.trim().startsWith("{") || responseBody.trim().startsWith("[")) {
				ExtentLogger.logJson(responseBody);
				FileLogger.logJson(responseBody);
			} else {
				ExtentLogger.logInfoDetails(responseBody);
				FileLogger.logInfoDetails(responseBody);
			}
		} else {
			ExtentLogger.logInfoDetails("No response body returned");
			FileLogger.logInfoDetails("No response body returned");
		}
		
		

	}

	public static Response performPost(String baseUrl, String basePath, String requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, null, null,
				requestPayload, headers);
		Response response = requestSpecification.post().then().extract().response();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String baseUrl, String basePath, Map<String, Object> requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, null, null,
				requestPayload, headers);

		Response response = requestSpecification.post().then().extract().response();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String baseUrl, String basePath, Booking requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, null, null,
				requestPayload, headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String baseUrl, String basePath, CreateBooking requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, null, null,
				requestPayload, headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String baseUrl, String basePath, BookingTestData requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, null, null,
				requestPayload, headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performGetBookingIds(String baseUrl, String basePath) {
		RequestSpecification requestSpec = getRequestSpecification(baseUrl, basePath, null, null, null, null);
		Response response = requestSpec.get();
		logRequestInReport(requestSpec, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performGetBooking(String baseUrl, String basePath, Integer bookingId,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, bookingId, null, null,
				headers);
		Response response = requestSpecification.get();

		logRequestInReport(requestSpecification, bookingId);
		logResponseInReport(response);
		return response;
	}

	public static Response performTokenPost(String baseUrl, String basePath, TokenCredentials tokenPayload,
			Map<String, String> headers) {
		return getRequestSpecification(baseUrl, basePath, null, null, tokenPayload, headers).post();
	}

	public static Response performUpdate(String baseUrl, String basePath, String accessToken, int bookingId,
			Booking updatedPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, bookingId, accessToken,
				updatedPayload, headers);
		Response response = requestSpecification.put();
		logRequestInReport(requestSpecification, bookingId);
		logResponseInReport(response);
		return response;
	}

	public static Response performPartialUpdate(String baseUrl, String basePath, String accessToken, int bookingId,
			Booking updatedPayload, Map<String, String> headers) {
		RequestSpecification requestSpec = getRequestSpecification(baseUrl, basePath, bookingId, accessToken,
				updatedPayload, headers);
		Response response = requestSpec.put();
		logRequestInReport(requestSpec, bookingId);
		logResponseInReport(response);
		return response;

	}

	public static Response performDelete(String baseUrl, String basePath, Integer bookingId, String accessToken,
			Map<String, String> headers) {
		RequestSpecification requestSpec = getRequestSpecification(baseUrl, basePath, bookingId, accessToken, null,
				headers);
		Response response = requestSpec.delete();
		logRequestInReport(requestSpec, bookingId);
		logResponseInReport(response);
		return response;

	}

	public static Response performPostUsingJson(String baseUrl, String basePath, Map<String, Object> bookingData,
			Map<String, String> headers) {
		String payload = readTemplate();

		// Replace placeholders
		for (Map.Entry<String, Object> entry : bookingData.entrySet()) {
			payload = payload.replace("{{" + entry.getKey() + "}}", entry.getValue().toString());
		}
		RequestSpecification requestSpec = getRequestSpecification(baseUrl, basePath, null, null, payload, headers);
		Response response = requestSpec.when().post().then().extract().response();
		logRequestInReport(requestSpec, null);
		logResponseInReport(response);
		return response;
	}

	private static String readTemplate() {
		String jsonString = "";
		try {
			jsonString = FileUtils.readFileToString(new File("src/test/resources/templates/booking_template.json"),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

}
