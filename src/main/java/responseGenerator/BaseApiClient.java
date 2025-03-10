package responseGenerator;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tokenHandling.OAuthClient;

public class BaseApiClient {
    private static final Logger logger = LoggerFactory.getLogger(BaseApiClient.class);
    private final String baseUrl;
    private final OAuthClient oAuthClient;

    public BaseApiClient(String baseUrl, OAuthClient oAuthClient) {
        this.baseUrl = baseUrl;
        this.oAuthClient = oAuthClient;
    }

    private RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + oAuthClient.getAccessToken())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    public Response get(String endpoint) {
        return executeWithRetry(() -> getRequestSpec().get(endpoint), endpoint);
    }

    public Response post(String endpoint, Object body) {
        return executeWithRetry(() -> getRequestSpec().body(body).post(endpoint), endpoint);
    }

    public Response put(String endpoint, Object body) {
        return executeWithRetry(() -> getRequestSpec().body(body).put(endpoint), endpoint);
    }

    public Response delete(String endpoint) {
        return executeWithRetry(() -> getRequestSpec().delete(endpoint), endpoint);
    }

    private Response executeWithRetry(ApiRequest request, String endpoint) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            attempt++;
            Response response = request.execute();
            logResponse(response, endpoint);

            if (response.getStatusCode() == 401) { // Handle expired token
                logger.warn("Token expired, refreshing...");
                oAuthClient.refreshAccessToken();
            } else if (response.getStatusCode() < 500) { // Success or client error
                return response;
            }

            logger.warn("Retrying request... Attempt: {}", attempt);
            try {
                TimeUnit.SECONDS.sleep(2); // Wait before retrying
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        throw new RuntimeException("Failed after " + maxRetries + " retries");
    }

    private void logResponse(Response response, String endpoint) {
        logger.info("API Request to: {}", endpoint);
        logger.info("Response Status: {}", response.getStatusCode());
        logger.info("Response Body: {}", response.asString());
    }

    @FunctionalInterface
    interface ApiRequest {
        Response execute();
    }
}


