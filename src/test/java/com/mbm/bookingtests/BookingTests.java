package com.mbm.bookingtests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbm.bookingpojos.Booking;
import com.mbm.bookingpojos.BookingResponse;
import com.mbm.bookingpojos.TokenCredentials;
import com.mbm.payload.Payloads;
import com.mbm.reporting.ExtentReportManager;
import com.mbm.restutils.AssertionUtil;

import io.restassured.response.Response;

public class BookingTests extends BookingAPIs {

	public Integer bookingId;
	public String accessToken;

	// @BeforeMethod(alwaysRun = true)
	public void setupTestLogging(Method method, Object[] testData) {
		String testName = method.getName() + " - " + Arrays.toString(testData);
		ExtentReportManager.createTest(testName);
	}

	// @Test
	public void createBooking() throws StreamReadException, DatabindException, IOException {

		List<String> bookingdates = Arrays.asList("2018-01-01", "2019-01-01");
		// String payload = Payloads.getCreateBookingPayload("java", "selenium", 1000,
		// true, bookingdates, "super bowls");
		// Map<String, Object> payload = Payloads.getCreateBookingPayloadFromMap("java",
		// "selenium", 1000, true, bookingdates, "super bowls");
		// Map<String, Object> payload = Payloads.getCreateBookingPayloadFromMap();
		// Booking payload = Payloads.getCreateBookingPayloadFromPojo();
		// Response response = createBooking(payload);

		Booking payload = new Booking();
		String serializeData = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(payload);

		// System.out.println(serializeData);
		// Assert.assertEquals(response.statusCode(), 200);

	}

	@Test(priority = 1, dataProvider = "BookingDataByPoiji", dataProviderClass = com.mbm.testutils.DataProviders.class)

	public void createBookingAndVerifyResponse(Booking requestPayload, Method m)
			throws JsonMappingException, JsonProcessingException {
		// Booking requestPayload = Payloads.getCreateBookingPayloadFromPojo();
		Response response = createBooking(requestPayload);
		bookingId = response.jsonPath().getInt("bookingid");
		Payloads.booking = response.jsonPath().getObject("booking", Booking.class);
		Map<String, Object> expectedValueMap = new HashMap<>();
		expectedValueMap.put("booking.firstname", requestPayload.getFirstname());
		expectedValueMap.put("booking.lastname", requestPayload.getLastname());
		expectedValueMap.put("booking.totalprice", requestPayload.getTotalprice());
		expectedValueMap.put("booking.depositpaid", requestPayload.isDepositpaid());
		expectedValueMap.put("booking.bookingdates.checkin", requestPayload.getBookingdates().getCheckin());
		expectedValueMap.put("booking.bookingdates.checkout", requestPayload.getBookingdates().getCheckout());
		expectedValueMap.put("booking.additionalneeds", requestPayload.getAdditionalneeds());
		// AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);
		AssertionUtil.assertExpectedValuesWithJsonPath(response, expectedValueMap);
		// JSONPath assertion (Direct)
		Assert.assertEquals(response.jsonPath().getString("booking.firstname"), requestPayload.getFirstname(),
				"Firstname mismatch!");
		Assert.assertEquals(response.jsonPath().getString("booking.lastname"), requestPayload.getLastname(),
				"Lastname mismatch!");

		// Deserialize into BookingResponse (which contains Booking object)
		ObjectMapper objectMapper = new ObjectMapper();
		BookingResponse createBookingResponse = objectMapper.readValue(response.asString(), BookingResponse.class);
		Booking responseBooking = createBookingResponse.getBooking(); // Extract actual booking data
		// Assertions on deserialized object
		Assert.assertEquals(responseBooking.getFirstname(), requestPayload.getFirstname(), "Firstname mismatch!");
		Assert.assertEquals(responseBooking.getLastname(), requestPayload.getLastname(), "Lastname mismatch!");
		Assert.assertEquals(responseBooking.getTotalprice(), requestPayload.getTotalprice(), "Total price mismatch!");
		Assert.assertEquals(responseBooking.isDepositpaid(), requestPayload.isDepositpaid(), "Deposit paid mismatch!");
		Assert.assertEquals(responseBooking.getAdditionalneeds(), requestPayload.getAdditionalneeds(),
				"Additional needs mismatch!");
		Assert.assertEquals(responseBooking.getBookingdates().getCheckin(),
				requestPayload.getBookingdates().getCheckin(), "Checkin date mismatch!");
		Assert.assertEquals(responseBooking.getBookingdates().getCheckout(),
				requestPayload.getBookingdates().getCheckout(), "Checkout date mismatch!");

		// Verify Booking ID is present
		Assert.assertTrue(createBookingResponse.getBookingid() > 0, "Invalid Booking ID!");

		Assert.assertEquals(responseBooking, requestPayload);

		System.out.println("✅ All Assertions Passed! in " + m.getName());

	}

	@Test(priority = 0)
	public void getBookingIdsAndVerifyResponse(Method m) {
		Response response = getBookingIds();
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("✅ All Assertions Passed! in " + m.getName());
	}

	@Test(priority = 2)
	public void getBookingDetailsAndVerifyResponse(Method m) {
		Response response = getBookingDetails(bookingId);
		// response.prettyPrint();

		Assert.assertEquals(response.jsonPath().getString("firstname"), "mbm");
		Assert.assertEquals(response.jsonPath().getString("lastname"), "mannepalli");
		System.out.println("✅ All Assertions Passed! in " + m.getName());

	}

	@Test(priority = 3)
	public void getAccessTokenAndVerifyResponse(Method m) {
		TokenCredentials tokenPayload = Payloads.getCreateTokenPayload();
		Response response = getAccessToken(tokenPayload);
		accessToken = response.jsonPath().getString("token");
		System.out.println("✅ All Assertions Passed! in " + m.getName());
	}

	@Test(priority = 4)
	public void getUpdateBookingAndVerifyResponse(Method m) {
		Booking updatedPayload = Payloads.getUpdateBookingPayloadFromPojo();
		Response response = updateBooking(accessToken, bookingId, updatedPayload);
		Payloads.booking = updatedPayload;
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("✅ All Assertions Passed! in " + m.getName());

	}

	@Test(priority = 5)
	public void getPartialUpdateBooking(Method m) {
		Booking partiallyUpdatedBooking = Payloads.getPartialUpdateBookingPayloadFromPojo();
		partiallyUpdateBooking(accessToken, bookingId, partiallyUpdatedBooking);
		System.out.println("✅ All Assertions Passed! in " + m.getName());
	}

	@Test(priority = 6)
	public void getDeleteBooking(Method m) {
		Response response = deleteBooking(bookingId, accessToken);
		Assert.assertEquals(response.statusCode(), 204);
		System.out.println("✅ All Assertions Passed! in " + m.getName());
	}

}
