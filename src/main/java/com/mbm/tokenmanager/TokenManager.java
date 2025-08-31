package com.mbm.tokenmanager;

import static com.mbm.config.EnvironmentConfig.getConfig;

import java.util.HashMap;

import com.mbm.bookingpojos.TokenCredentials;
import com.mbm.payload.Payloads;
import com.mbm.restutils.RestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {
    private static String accessToken;
    private static long tokenExpiryTime;

    public static String getAccessToken() {
        if (accessToken == null || isTokenExpired()) {
        	generateNewOAuthToken();
        }
        return accessToken;
    }
    
    public static String getCookieToken() {
    	TokenCredentials tokenPayload = Payloads.getCreateTokenPayload();
		Response response = getCookieAccessToken(tokenPayload);
		return response.jsonPath().getString("token");
    }
    
    public static Response getCookieAccessToken(TokenCredentials tokenPayload) {
		String baseUrl = getConfig().getBaseUrl();
		String basePath = getConfig().getEndpoints().getGetAccessToken();
		return RestUtils.performTokenPost(baseUrl, basePath, tokenPayload, new HashMap<>());
	}

    private static boolean isTokenExpired() {
        return System.currentTimeMillis() >= tokenExpiryTime;
    }

    private static void generateNewOAuthToken() {
        Response response = RestAssured.given()
            .auth().preemptive().basic("client_id", "client_secret")
            .contentType("application/x-www-form-urlencoded")
            .formParam("grant_type", "client_credentials")
        .when()
            .post("https://auth.example.com/oauth/token");

        accessToken = response.jsonPath().getString("access_token");
        int expiresIn = response.jsonPath().getInt("expires_in"); // seconds

        // Set expiry buffer of 30 seconds to avoid near-expiry usage
        tokenExpiryTime = System.currentTimeMillis() + (expiresIn - 30) * 1000L;
    }
}

