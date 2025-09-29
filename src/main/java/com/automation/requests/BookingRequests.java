package com.automation.requests;

import com.automation.config.ConfigReader;
import com.automation.constants.Endpoints;
import com.automation.constants.HeaderParamaters;
import com.automation.constants.JsonProperties;
import com.automation.model.AuthRequestModel;
import com.automation.model.BookingRequestModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingRequests {

    private static final String BASE_URL = ConfigReader.getBaseUrl();

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public Response createBooking(BookingRequestModel booking) {
        return given()
                .header(HeaderParamaters.CONTENT_TYPE, HeaderParamaters.APPLICATION_JSON)
                .body(booking)
                .when()
                .post(Endpoints.BOOKING);
    }

    public Response getBookingById(int bookingId) {
        return given()
                .when()
                .get(Endpoints.BOOKING + "/" + bookingId);
    }

    public Response getAllBookings() {
        return given()
                .when()
                .get(Endpoints.BOOKING);
    }

    public Response deleteBooking(int bookingId, String token) {
        return given()
                .header(HeaderParamaters.COOKIE, HeaderParamaters.TOKEN + token)
                .when()
                .delete(Endpoints.BOOKING + "/" + bookingId);
    }

    public Response updateBooking(String token, int bookingId, BookingRequestModel booking) {
        return given()
                .header(HeaderParamaters.COOKIE, HeaderParamaters.TOKEN + token)
                .body(booking)
                .when()
                .put(Endpoints.BOOKING + "/" + bookingId);
    }

    public Response partialUpdateBooking(String token, int bookingId, BookingRequestModel booking) {
        return given()
                .header(HeaderParamaters.COOKIE, HeaderParamaters.TOKEN + token)
                .body(booking)
                .when()
                .patch(Endpoints.BOOKING + "/" + bookingId);
    }

    public String createAuthToken(String username, String password) {
        AuthRequestModel authRequest = new AuthRequestModel(username, password);
        return given()
                .header(HeaderParamaters.CONTENT_TYPE, HeaderParamaters.APPLICATION_JSON)
                .body(authRequest)
                .when()
                .post(Endpoints.AUTH)
                .then()
                .extract()
                .jsonPath()
                .getString(JsonProperties.Keys.TOKEN);
    }
}
