package bookings;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import excelUtils.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.BookingConverter;
import payload.CreateBooking;

public class BookingTestScenario {
    public static void main(String[] args) throws Exception {
        // Read data from Excel
        List<Map<String, String>> excelData = ExcelUtils.getDataFromExcel("test-data/CreateBookingDataScenarios.xlsx", "Sheet1");
        List<CreateBooking> bookingList = BookingConverter.convertToCreateBookingList(excelData);

        ObjectMapper objectMapper = new ObjectMapper();

        for (CreateBooking booking : bookingList) {
            String requestBody = objectMapper.writeValueAsString(booking);
            
            System.out.println("\nScenario: " + booking.getScenarioId());
            System.out.println("Request Body: " + requestBody);

            Response response = RestAssured.given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/booking")
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post();

            // Validate status code
            int expectedStatus = booking.getExpectedStatusCode();
            if (response.statusCode() == expectedStatus) {
                System.out.println("✅ Scenario Passed: " + booking.getScenarioId());
            } else {
                System.out.println("❌ Scenario Failed: " + booking.getScenarioId() + 
                                   " | Expected: " + expectedStatus + 
                                   " | Actual: " + response.statusCode());
            }
        }
    }
}
