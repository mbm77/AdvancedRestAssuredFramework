package com.mbm.bookingtests;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.mbm.reporting.ExtentReportManager;
import com.mbm.testutils.DataProviders;

import io.restassured.response.Response;

public class JsonDataDrivenTests extends BookingAPIs{
	
	@Test(dataProvider = "bookingDataFromJsonFile", dataProviderClass = DataProviders.class)
    public void createBookingTest(Map<String, Object> bookingData,Method m) throws Exception {
		//ExtentReportManager
		//.createTest("Test Name "+m.getName());
		Response response = createBokingByJson(bookingData);
        response.prettyPrint();

       
    }
	
	
}
