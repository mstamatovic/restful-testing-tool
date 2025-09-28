package com.automation.service;

import com.automation.config.ConfigReader;
import com.automation.model.AuthRequestModel;
import com.automation.model.BookingRequestModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingService {

    private static final String BASE_URL = ConfigReader.getBaseUrl();
    private static final String BOOKING_ENDPOINT = "/booking";
    private static final String AUTH_ENDPOINT = "/auth";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public Response createBooking(BookingRequestModel booking) {
        return given()
                .header("Content-Type", "application/json")
                .body(booking)
                .when()
                .post(BOOKING_ENDPOINT);
    }

    public Response getBookingById(int bookingId) {
        return given()
                .when()
                .get(BOOKING_ENDPOINT + "/" + bookingId);
    }

    public Response getAllBookings() {
        return given()
                .when()
                .get(BOOKING_ENDPOINT);
    }

    public Response deleteBooking(int bookingId, String token) {
        return given()
                .header("Cookie", "token=" + token)
//                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete(BOOKING_ENDPOINT + "/" + bookingId);
    }

    public Response updateBooking(int bookingId, BookingRequestModel booking) {
        return given()
//                .header("Cookie", "token=" + token)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(booking)
                .when()
                .put(BOOKING_ENDPOINT + "/" + bookingId);
    }

    public Response partialUpdateBooking(String token, int bookingId, BookingRequestModel booking) {
        return given()
                .header("Cookie", "token=" + token)
//                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(booking)
                .when()
                .patch(BOOKING_ENDPOINT + "/" + bookingId);
    }

    public String createAuthToken(String username, String password) {
        AuthRequestModel authRequest = new AuthRequestModel(username, password);
        return given()
                .header("Content-Type", "application/json")
                .body(authRequest)
                .when()
                .post("/auth")
                .then()
                .extract()
                .jsonPath()
                .getString("token");
    }
}
