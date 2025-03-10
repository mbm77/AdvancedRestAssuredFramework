package com.mbm.testutils;

import static com.mbm.utils.HandlingNullValuesInBuilderPattern.handleNoData;
import static com.mbm.utils.HandlingNullValuesInBuilderPattern.handleNoDataBoolean;
import static com.mbm.utils.HandlingNullValuesInBuilderPattern.handleNoDataInteger;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;

import com.mbm.bookingpojos.Booking;
import com.mbm.bookingpojos.BookingDates;
import com.mbm.bookingpojos.CreateBooking;
import com.mbm.poijiutils.BookingExcel;
import com.mbm.utils.ExcelUtils;
import com.poiji.bind.Poiji;;

public class DataProviders {
	@DataProvider(name = "BookingData")
	public Iterator<Booking> getBookingData() {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/test-data/test-data.xlsx";
		List<Map<String, String>> excelData = ExcelUtils.getDataFromExcel(filePath, "Sheet1");
		List<Booking> bookingData = new ArrayList<>();
		for (Map<String, String> data : excelData) {
			Booking booking = Booking.builder().firstname(data.get("firstname")).lastname(data.get("lastname"))
					.totalprice(Integer.parseInt(data.get("totalprice")))
					.depositpaid(Boolean.parseBoolean(data.get("depositpaid").toLowerCase()))
					.bookingdates(
							BookingDates.builder().checkin(data.get("checkin")).checkout(data.get("checkout")).build())
					.additionalneeds(data.get("additionalneeds")).build();
			bookingData.add(booking);
		}
		return bookingData.iterator();
	}

	@DataProvider(name = "BookingDataByPoiji")
	public static Iterator<Booking> getBookingDataUsingPoiji() {
		// PoijiOptions option =
		// PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/test-data/BookingData.xlsx");

		// Convert Excel data to List of Booking objects
		List<BookingExcel> bookingExcelList = Poiji.fromExcel(file, BookingExcel.class);

		// Use Streams to print data by calling toString() method on each BookingExcel
		// object
		//bookingExcelList.stream().forEach(booking -> System.out.println(booking.toString()));

		// Convert BookingExcel to Booking POJOs and return the list
		List<Booking> bookingList = bookingExcelList.stream().filter(BookingExcel -> BookingExcel.getEnabled() == true)
				.map(BookingExcel::toBooking).collect(Collectors.toList());
		return bookingList.iterator();
	}

	@DataProvider(name = "BookingDataScenarioExcel")
	public Iterator<CreateBooking> getBookingDataFromExcel() {
		String filePath = System.getProperty("user.dir")
				+ "/src/test/resources/test-data/CreateBookingDataScenarios.xlsx";
		List<Map<String, String>> excelData = ExcelUtils.getDataFromExcel(filePath, "Sheet2");
		// List<CreateBooking> bookingList =
		// BookingConverter.convertToCreateBookingList(excelData);
		List<CreateBooking> testDataList = new ArrayList<>();
		CreateBooking createBooking = null;
		excelData = excelData.stream().filter(mapData -> mapData.get("Enabled").equalsIgnoreCase("yes"))
				.collect(Collectors.toList());
		for (Map<String, String> data : excelData) {

			createBooking = getCustomizedBookingData1(data);

			/*
			 * BasePojo basePojo = new BasePojo(data.get("ScenarioID"),
			 * data.get("ScenarioDesc"), Integer.parseInt(data.get("ExpectedStatusCode")),
			 * data.get("ExpectedErrorMessage"));
			 * 
			 * testData = new BookingTestData(createBooking, basePojo);
			 * //System.out.println("✅ Debug (Loop): " + testData); // Debug Line
			 * 
			 */
			testDataList.add(createBooking);
		}

		return testDataList.iterator();

	}

	private CreateBooking getCustomizedBookingData(Map<String, String> data) {
		CreateBooking createBooking = CreateBooking.builder().firstname(handleNoData(data.get("firstname")))
				.lastname(handleNoData(data.get("lastname"))).totalprice(handleNoDataInteger(data.get("totalprice")))
				.depositpaid(handleNoDataBoolean(data.get("depositpaid")))
				.bookingdates((handleNoData(data.get("checkin")) != null && handleNoData(data.get("checkout")) != null)
						? new BookingDates(handleNoData(data.get("checkin")), handleNoData(data.get("checkout")))
						: null)
				.additionalneeds(handleNoData(data.get("additionalneeds"))).build();

		return createBooking;
	}

	private CreateBooking getCustomizedBookingData1(Map<String, String> data) {
		CreateBooking createBooking = new CreateBooking();
		createBooking.setScenarioId(data.get("ScenarioID"));
		createBooking.setScenarioDesc(data.get("ScenarioDesc"));
		createBooking.setExpectedStatusCode(Integer.parseInt(data.get("ExpectedStatusCode")));
		createBooking.setExpectedErrorMessage(data.get("ExpectedErrorMessage"));

		if (!data.get("ExpectedErrorMessage").equals("NO_DATA"))
			createBooking.setExpectedErrorMessage(data.get("ExpectedErrorMessage"));
		if (!data.get("firstname").equalsIgnoreCase("NO_DATA"))
			createBooking.setFirstname((data.get("firstname")));
		if (!data.get("lastname").equalsIgnoreCase("NO_DATA"))
			createBooking.setLastname((data.get("lastname")));
		if (!data.get("totalprice").equalsIgnoreCase("NO_DATA"))
			createBooking.setTotalprice(Integer.parseInt(data.get("totalprice")));
		if (!data.get("depositpaid").equalsIgnoreCase("NO_DATA"))
			createBooking.setDepositpaid(Boolean.parseBoolean(data.get("depositpaid")));
		if (!data.get("checkin").equalsIgnoreCase("NO_DATA") && !data.get("checkout").equalsIgnoreCase("NO_DATA"))
			createBooking.setBookingdates(new BookingDates(data.get("checkin"), data.get("checkout")));
		if (!data.get("additionalneeds").equalsIgnoreCase("NO_DATA"))
			createBooking.setAdditionalneeds(data.get("additionalneeds"));

		return createBooking;

	}
}
