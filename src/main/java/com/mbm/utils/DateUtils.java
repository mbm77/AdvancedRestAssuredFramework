package com.mbm.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static String getCheckinDate() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String getCheckoutDate() {
		return LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String getDateFormat(String date) {
		String inputDate = date;

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate localDate = LocalDate.parse(inputDate, inputFormatter);

		return localDate.format(outputFormatter);
	}
}
