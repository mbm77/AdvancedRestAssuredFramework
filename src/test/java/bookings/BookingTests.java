package bookings;

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

import io.restassured.response.Response;
import payload.Booking;
import payload.BookingResponse;
import reporting.ExtentReportManager;
import restUtils.AssertionUtil;

public class BookingTests extends BookingAPIs {

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

		System.out.println(serializeData);
		// Assert.assertEquals(response.statusCode(), 200);

	}

	@Test(dataProvider = "BookingDataByPoiji", dataProviderClass = testUtils.DataProviders.class)
	public void createBookingAndVerifyResponse(Booking requestPayload)
			throws JsonMappingException, JsonProcessingException {
		// Booking requestPayload = Payloads.getCreateBookingPayloadFromPojo();
		Response response = createBooking(requestPayload);
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

		System.out.println("✅ All Assertions Passed!");

	}

}
