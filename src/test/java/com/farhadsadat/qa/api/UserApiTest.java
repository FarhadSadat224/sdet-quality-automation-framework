package com.farhadsadat.qa.api;

import com.farhadsadat.qa.client.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class UserApiTest {
    private static WireMockServer api;

    @BeforeAll
    static void startApi() throws Exception {
        api = new WireMockServer(0);
        api.start();
        RestAssured.baseURI = "http://localhost:" + api.port();

        String response = new ObjectMapper()
                .writeValueAsString(new User(42, "Sayed Farhad Sadat", "SDET", true));

        api.stubFor(get(urlEqualTo("/api/users/42"))
                .withHeader("X-Correlation-Id", com.github.tomakehurst.wiremock.client.WireMock.equalTo("portfolio-test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }

    @AfterAll
    static void stopApi() {
        api.stop();
        RestAssured.reset();
    }

    @Test
    @DisplayName("GET /users/{id} returns a valid active SDET user")
    void returnsUserContract() {
        given()
                .header("X-Correlation-Id", "portfolio-test")
                .pathParam("id", 42)
        .when()
                .get("/api/users/{id}")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(42))
                .body("name", equalTo("Sayed Farhad Sadat"))
                .body("role", equalTo("SDET"))
                .body("active", equalTo(true));

        api.verify(1, getRequestedFor(urlEqualTo("/api/users/42")));
    }
}
