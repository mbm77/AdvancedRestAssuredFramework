package bookings;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import payload.Booking;
import payload.BookingTestData;
import payload.CreateBooking;
import restUtils.RestUtils;

public class BookingAPIs {

	public Response createBooking(Map<String, Object> createBookingAPIPayload) {
		String endPoint = (String) Base.dataFromJsonFile.get("createbooking");
		return RestUtils.performPost(endPoint, createBookingAPIPayload, new HashMap<>());
	}
	
	public Response createBooking(Booking createBookingAPIPayload) {
		String endPoint = (String) Base.dataFromJsonFile.get("createbooking");
		return RestUtils.performPost(endPoint, createBookingAPIPayload, new HashMap<>());
	}
	
	public Response createBooking(CreateBooking createBookingAPIPayload) {
		String endPoint = (String) Base.dataFromJsonFile.get("createbooking");
		return RestUtils.performPost(endPoint, createBookingAPIPayload, new HashMap<>());
	}
	
	public Response createBooking(BookingTestData createBookingAPIPayload) {
		String endPoint = (String) Base.dataFromJsonFile.get("createbooking");
		return RestUtils.performPost(endPoint, createBookingAPIPayload, new HashMap<>());
	}

}
