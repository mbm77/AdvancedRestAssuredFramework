package payload;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingConverter {
    public static List<CreateBooking> convertToCreateBookingList(List<Map<String, String>> excelData) {
        return excelData.stream()
                .map(BookingConverter::mapToCreateBooking)
                .collect(Collectors.toList());
    }

    private static CreateBooking mapToCreateBooking(Map<String, String> data) {
        CreateBooking.CreateBookingBuilder builder = CreateBooking.builder();

        // Set common fields
        builder.scenarioId(data.getOrDefault("ScenarioID", ""))
               .scenarioDesc(data.getOrDefault("ScenarioDesc", ""))
               .expectedStatusCode(Integer.parseInt(data.getOrDefault("ExpectedStatusCode", "200")))
               .expectedErrorMessage(data.getOrDefault("ExpectedErrorMessage", ""));

        // Apply conditions based on Scenario ID
        String scenarioId = data.get("ScenarioID");

        if (scenarioId.contains("EmptyPayload")) {
            // Scenario: Empty Payload (send empty request)
            return builder.build();
        } 
        if (scenarioId.contains("OnlyID")) {
            // Scenario: Only ID provided
            return builder.firstname("Test").build();
        } 
        if (scenarioId.contains("WithoutID")) {
            // Scenario: Exclude ID but provide other details
            return builder.lastname("Doe")
                           .totalprice(1000)
                           .depositpaid(true)
                           .bookingdates(BookingDates.builder()
                                   .checkin("2025-02-10")
                                   .checkout("2025-02-20")
                                   .build())
                           .additionalneeds("Lunch")
                           .build();
        } 
        if (scenarioId.contains("HappyPath")) {
            // Scenario: Full valid payload
            return builder.firstname(data.getOrDefault("firstname", "John"))
                           .lastname(data.getOrDefault("lastname", "Doe"))
                           .totalprice(Integer.parseInt(data.getOrDefault("totalprice", "1000")))
                           .depositpaid(Boolean.parseBoolean(data.getOrDefault("depositpaid", "true")))
                           .bookingdates(BookingDates.builder()
                                   .checkin(data.getOrDefault("checkin", "2025-02-15"))
                                   .checkout(data.getOrDefault("checkout", "2025-02-22"))
                                   .build())
                           .additionalneeds(data.getOrDefault("additionalneeds", "Breakfast"))
                           .build();
        }

        // Default case (no special handling)
        return builder.build();
    }
}
