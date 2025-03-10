package tokenHandling;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuthClient {
    private final String authUrl;
    private final String clientId;
    private final String clientSecret;
    private String accessToken;

    public OAuthClient(String authUrl, String clientId, String clientSecret) {
        this.authUrl = authUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        refreshAccessToken();
    }

    public void refreshAccessToken() {
        Response response = RestAssured.given()
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .post(authUrl);

        if (response.getStatusCode() == 200) {
            accessToken = response.jsonPath().getString("access_token");
        } else {
            throw new RuntimeException("Failed to obtain access token: " + response.asString());
        }
    }

    public String getAccessToken() {
        return accessToken;
    }
}

