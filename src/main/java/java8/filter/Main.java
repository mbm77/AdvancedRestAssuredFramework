package java8.filter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    // Fetch series names based on startYear and endYear
    public static List<String> getSeries(int seriesStartYear, int seriesEndYear) {
        List<String> seriesList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            int pageNo = 1;
            int totalPages = 1;

            do {
                URL url = new URL("https://jsonmock.hackerrank.com/api/tvseries?page=" + pageNo);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                    JsonNode rootNode = mapper.readTree(sb.toString());
                    totalPages = rootNode.get("total_pages").asInt(); // read total pages dynamically
                    JsonNode seriesData = rootNode.get("data");

                    for (JsonNode series : seriesData) {
                        String strYears = series.get("runtime_of_series").asText();
                        String[] yearsArr = strYears.replaceAll("[()]", "").split("-");
                        int startYear = Integer.parseInt(yearsArr[0].trim());
                        int endYear = yearsArr[1].trim().isEmpty() ? -1 : Integer.parseInt(yearsArr[1].trim());

                        if (matchesYearRange(startYear, endYear, seriesStartYear, seriesEndYear)) {
                            seriesList.add(series.get("name").asText());
                        }
                    }
                } finally {
                    con.disconnect();
                }

                pageNo++;
            } while (pageNo <= totalPages);

        } catch (Exception e) {
            System.out.println("Error fetching series: " + e.getMessage());
        }

        return seriesList;
    }

    // Check if series matches the input range
    private static boolean matchesYearRange(int startYear, int endYear, int inputStart, int inputEnd) {
        if (inputEnd == -1) { // ongoing series case
            return startYear >= inputStart && endYear == -1;
        } else { // specific year range
            return startYear >= inputStart && (endYear != -1 && endYear <= inputEnd);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter start year (yyyy):");
            int startYear = scanner.nextInt();
            System.out.println("Enter end year (yyyy, -1 for ongoing series):");
            int endYear = scanner.nextInt();

            List<String> seriesNameList = getSeries(startYear, endYear);
            if (seriesNameList.isEmpty()) {
                System.out.println("No series found for the given range.");
            } else {
                System.out.println(seriesNameList);
            }
        }
    }
}
