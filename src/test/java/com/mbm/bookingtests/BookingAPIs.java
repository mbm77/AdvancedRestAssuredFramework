package com.mbm.bookingtests;

import java.util.HashMap;
import java.util.Map;

import com.mbm.bookingpojos.Booking;
import com.mbm.bookingpojos.BookingTestData;
import com.mbm.bookingpojos.CreateBooking;
import com.mbm.bookingpojos.TokenCredentials;
import static com.mbm.config.EnvironmentConfig.*;
import com.mbm.restutils.RestUtils;

import io.restassured.response.Response;

public class BookingAPIs {

	public Response createBooking(Map<String, Object> createBookingAPIPayload) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getCreateBooking();
		return RestUtils.performPost(baseUrl, basePath, createBookingAPIPayload, new HashMap<>());
	}

	public Response createBooking(Booking createBookingAPIPayload) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getCreateBooking();
		return RestUtils.performPost(baseUrl, basePath, createBookingAPIPayload, new HashMap<>());
	}

	public Response createBooking(CreateBooking createBookingAPIPayload) {

		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getCreateBooking();
		return RestUtils.performPost(baseUrl, basePath, createBookingAPIPayload, new HashMap<>());
	}

	public Response createBooking(BookingTestData createBookingAPIPayload) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getCreateBooking();
		return RestUtils.performPost(baseUrl, basePath, createBookingAPIPayload, new HashMap<>());
	}

	public Response getBookingIds() {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getGetBookingIds();
		return RestUtils.performGetBookingIds(baseUrl, basePath);
	}

	public Response getBookingDetails(Integer bookingId) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getGetBooking();
		return RestUtils.performGetBooking(baseUrl, basePath, bookingId, new HashMap<>());
	}

	public Response getAccessToken(TokenCredentials tokenPayload) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getGetAccessToken();
		return RestUtils.performTokenPost(baseUrl, basePath, tokenPayload, new HashMap<>());
	}

	public Response updateBooking(String accessToken, Integer bookingId, Booking updatedPayload) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getUpdateBooking();
		return RestUtils.performUpdate(baseUrl, basePath, accessToken, bookingId, updatedPayload, new HashMap<>());
	}

	public Response partiallyUpdateBooking(String accessToken, Integer bookingId, Booking updatedPayload) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getPartialUpdateBooking();
		return RestUtils.performPartialUpdate(baseUrl, basePath, accessToken, bookingId, updatedPayload,
				new HashMap<>());
	}

	public Response deleteBooking(Integer bookingId, String accessToken) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getDeleteBooking();
		return RestUtils.performDelete(baseUrl, basePath, bookingId, accessToken, new HashMap<>());
	}

	public Response createBokingByJson(Map<String, Object> createBokingByJsondata) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getCreateBooking();
		return RestUtils.performPostUsingJson(baseUrl, basePath, createBokingByJsondata, new HashMap<>());
	}

}
