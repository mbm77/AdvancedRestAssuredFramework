package com.mbm.bookingtests;

import java.util.HashMap;
import java.util.Map;

import com.mbm.bookingpojos.Booking;
import com.mbm.bookingpojos.BookingTestData;
import com.mbm.bookingpojos.CreateBooking;
import com.mbm.bookingpojos.TokenCredentials;
import com.mbm.restutils.RestUtils;

import io.restassured.response.Response;

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

	public Response getBookingIds() {
		String endPoint = (String) Base.dataFromJsonFile.get("getBookingIds");
		return RestUtils.performGetBookingIds(endPoint);
	}

	public Response getBookingDetails(Integer bookingId) {
		String endPoint = (String) Base.dataFromJsonFile.get("getBooking");
		return RestUtils.performGetBooking(endPoint, bookingId, new HashMap<>());
	}

	public Response getAccessToken(TokenCredentials tokenPayload) {
		String endPoint = (String) Base.dataFromJsonFile.get("getAccessToken");
		return RestUtils.performTokenPost(endPoint, tokenPayload, new HashMap<>());
	}

	public Response updateBooking(String accessToken, Integer bookingId, Booking updatedPayload) {
		String endPoint = (String) Base.dataFromJsonFile.get("updateBooking");
		return RestUtils.performUpdate(endPoint, accessToken, bookingId, updatedPayload, new HashMap<>());
	}

	public Response partiallyUpdateBooking(String accessToken, Integer bookingId, Booking updatedPayload) {
		String endPoint = (String) Base.dataFromJsonFile.get("partialUpdateBooking");
		return RestUtils.performPartialUpdate(endPoint, accessToken, bookingId, updatedPayload, new HashMap<>());
	}

	public Response deleteBooking(Integer bookingId, String accessToken) {
		String endPoint = (String) Base.dataFromJsonFile.get("deleteBooking");
		return RestUtils.performDelete(endPoint, bookingId, accessToken, new HashMap<>());
	}

}
