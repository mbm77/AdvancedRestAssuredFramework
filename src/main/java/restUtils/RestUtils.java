package restUtils;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import payload.Booking;
import payload.BookingTestData;
import payload.CreateBooking;
import reporting.ExtentLogger;

public class RestUtils {

	private static RequestSpecification getRequestSpecification(String endPoint, Object requestPayload,
			Map<String, String> headers) {
		return RestAssured.given().baseUri(endPoint).headers(headers).contentType(ContentType.JSON)
				.body(requestPayload);
	}

	private static void logRequestInReport(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);

		ExtentLogger.logInfoDetails("Endpoint is " + queryableRequestSpecification.getBaseUri());
		ExtentLogger.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
		ExtentLogger.logInfoDetails("Request Headers are ");
		ExtentLogger.logHeaders(queryableRequestSpecification.getHeaders().asList());
		ExtentLogger.logInfoDetails("Request Body is");
		ExtentLogger.logJson(queryableRequestSpecification.getBody());
	}

	private static void logResponseInReport(Response response) {
		ExtentLogger.logInfoDetails("Response status is " + response.getStatusCode());
		ExtentLogger.logInfoDetails("Response Headers are ");
		ExtentLogger.logHeaders(response.getHeaders().asList());
		ExtentLogger.logInfoDetails("Response Body is ");
		ExtentLogger.logJson(response.getBody().prettyPrint());

	}

	public static Response performPost(String endPoint, String requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
		Response response = requestSpecification.post().then().log().all().extract().response();
		logRequestInReport(requestSpecification);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, Map<String, Object> requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);

		Response response = requestSpecification.post().then().extract().response();
		logRequestInReport(requestSpecification);
		logResponseInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, Booking requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification);
		logResponseInReport(response);
		return response;
	}
	
	public static Response performPost(String endPoint, CreateBooking requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification);
		logResponseInReport(response);
		return response;
	}
	
	public static Response performPost(String endPoint, BookingTestData requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);

		Response response = requestSpecification.post();
		logRequestInReport(requestSpecification);
		logResponseInReport(response);
		return response;
	}

}
