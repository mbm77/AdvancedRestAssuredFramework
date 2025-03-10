package com.mbm.restutils;

import java.util.HashMap;
import java.util.Map;

import com.mbm.bookingpojos.Booking;
import com.mbm.bookingpojos.BookingTestData;
import com.mbm.bookingpojos.CreateBooking;
import com.mbm.bookingpojos.TokenCredentials;
import com.mbm.reporting.ExtentLogger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class RestUtils {

	private static RequestSpecification getRequestSpecification(String endPoint, Integer bookingId, String accessToken,
			Object requestPayload, Map<String, String> headers) {

		RequestSpecification requestSpec = RestAssured.given().baseUri(endPoint).contentType(ContentType.JSON);

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

		String method = queryableRequestSpecification.getMethod();
		ExtentLogger.logInfoDetails("Method is " + method);

		ExtentLogger.logInfoDetails("Request Headers are ");
		ExtentLogger.logHeaders(queryableRequestSpecification.getHeaders().asList());

		if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
			Object requestBody = queryableRequestSpecification.getBody();
			String bodyContent = (requestBody != null) ? requestBody.toString() : "No request body";
			ExtentLogger.logInfoDetails("Request Body is");
			ExtentLogger.logJson(bodyContent);
		} else {
			ExtentLogger.logInfoDetails("No Request Body for " + method + " Method");
		}
	}

	private static void logResponseInReport(Response response) {
		ExtentLogger.logInfoDetails("Response status is " + response.getStatusCode());
		ExtentLogger.logInfoDetails("Response Headers are ");
		ExtentLogger.logHeaders(response.getHeaders().asList());

		String responseBody = response.getBody() != null ? response.getBody().asString().trim() : "";

		if (!responseBody.isEmpty()) {
		    ExtentLogger.logInfoDetails("Response Body is ");

		    if (responseBody.trim().startsWith("{") || responseBody.trim().startsWith("[")) {
		        ExtentLogger.logJson(responseBody); 
		    } else {
		        ExtentLogger.logInfoDetails(responseBody);
		    }
		} else {
		    ExtentLogger.logInfoDetails("No response body returned");
		}


	}

	public static Response performPost(String endPoint, String requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, null, null, requestPayload,
				headers);
		Response response = requestSpecification.post().then().extract().response();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, Map<String, Object> requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, null, null, requestPayload,
				headers);

		Response response = requestSpecification.post().then().extract().response();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, Booking requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, null, null, requestPayload,
				headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, CreateBooking requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, null, null, requestPayload,
				headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, BookingTestData requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, null, null, requestPayload,
				headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performGetBookingIds(String endPoint) {
		RequestSpecification requestSpec = getRequestSpecification(endPoint, null, null, null, null);
		Response response = requestSpec.get();
		logRequestInReport(requestSpec, null);
		logResponseInReport(response);
		return response;
	}

	public static Response performGetBooking(String endPoint, Integer bookingId, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, bookingId, null, null, headers);
		Response response = requestSpecification.get("{bookingId}");

		logRequestInReport(requestSpecification, bookingId);
		logResponseInReport(response);
		return response;
	}

	public static Response performTokenPost(String endPoint, TokenCredentials tokenPayload,
			Map<String, String> headers) {
		return getRequestSpecification(endPoint, null, null, tokenPayload, headers).post();
	}

	public static Response performUpdate(String endPoint, String accessToken, int bookingId, Booking updatedPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, bookingId, accessToken,
				updatedPayload, headers);
		Response response = requestSpecification.put("{bookingId}");
		logRequestInReport(requestSpecification, bookingId);
		logResponseInReport(response);
		return response;
	}

	public static Response performPartialUpdate(String endPoint, String accessToken, int bookingId,
			Booking updatedPayload, Map<String, String> headers) {
		RequestSpecification requestSpec = getRequestSpecification(endPoint, bookingId, accessToken, updatedPayload,
				headers);
		Response response = requestSpec.put("{bookingId}");
		logRequestInReport(requestSpec, bookingId);
		logResponseInReport(response);
		return response;

	}

	public static Response performDelete(String endpoint, Integer bookingId, String accessToken,
			Map<String, String> headers) {
		RequestSpecification requestSpec = getRequestSpecification(endpoint, bookingId, accessToken, null, headers);
		Response response = requestSpec.delete("{bookingId}");
		logRequestInReport(requestSpec, bookingId);
		logResponseInReport(response);
		return response;

	}

}
