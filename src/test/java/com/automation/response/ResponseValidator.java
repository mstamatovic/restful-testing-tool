package com.automation.response;

import com.automation.constants.JsonProperties;
import com.automation.constants.Messages;
import com.automation.constants.ResponseMessages;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseValidator {

    public static void verifyBookingIdIsValid(int bookingId, Response response) {
        response.then()
                .statusCode(200);
        assertTrue(bookingId > 0, Messages.INVALID_BOOKING_ID);
    }

    public static void verifyGetBookingByIdIsFound(int bookingId, Response response) {
        ResponseHandler responseHandler = new ResponseHandler();
        response
                .then()
                .statusCode(200);

        assertEquals(JsonProperties.Values.MILOS, responseHandler.getBookingFirstNameFromResponse(response),
                Messages.EXPECTED_FIRST_NAME);
        assertEquals(JsonProperties.Values.STAMATOVIC, responseHandler.getBookingLastNameFromResponse(response),
                Messages.EXPECTED_LAST_NAME);
        assertEquals(JsonProperties.Values.TOTALPRICE,
                responseHandler.getBookingTotalPriceFromResponse(response), Messages.EXPECTED_TOTAL_PRICE);
        assertEquals(JsonProperties.Values.DEPOSITPAID, responseHandler.getBookingDepositPaidFromResponse(response),
                Messages.EXPECTED_DEPOSITPAID);
        assertEquals(JsonProperties.Values.ADDITIONAL_NEEDS, responseHandler.getBookingAdditionalNeedsFromResponse(response),
                Messages.EXPECTED_ADDITIONAL_NEEDS);
        assertEquals(JsonProperties.Values.CHECKIN, responseHandler.getBookingCheckInFromResponse(response),
                Messages.EXPECTED_CHECKIN);
        assertEquals(JsonProperties.Values.CHECKOUT, responseHandler.getBookingCheckOutFromResponse(response),
                Messages.EXPECTED_CHECKOUT);
    }

    public static void verifyBookingByIdIsNotFound(int bookingId, Response response) {
        response
                .then()
                .statusCode(404);
        assertTrue(response.asString().contains(ResponseMessages.NOT_FOUND));
    }

    public static void verifyDeleteBookingWithInvalidTokenIsForbidden(Response response) {
        response.then()
                .statusCode(403);
        assertTrue(response.asString().contains(ResponseMessages.FORBIDDEN));
    }

    public static void verifyBookingDeletedSuccessfully(int bookingId, Response response) {
        response.then()
                .statusCode(201);
        assertTrue(response.asString().contains(ResponseMessages.CREATED));
    }

    public static void verifyPartialUpdateBookingIsSuccessful(Response response) {
        response
                .then()
                .statusCode(200);
    }

    public static void verifyBookingIsPartiallyUpdated(Response response) {
        ResponseHandler responseHandler = new ResponseHandler();
        assertEquals(JsonProperties.Values.TOTALPRICE,
                responseHandler.getBookingTotalPriceFromResponse(response), Messages.TOTAL_PRICE_NOT_UPDATED);
    }

    public static void verifyUpdateBookingIsSuccessful(Response response) {
        response
                .then()
                .statusCode(200);
    }

    public static void verifyBookingIsUpdated(Response response) {
        ResponseHandler responseHandler = new ResponseHandler();
        response
                .then()
                .statusCode(200);

        assertEquals(JsonProperties.Values.MASA, responseHandler.getBookingFirstNameFromResponse(response),
                Messages.UPDATE_EXPECTED_FIRST_NAME);
        assertEquals(JsonProperties.Values.SPASIC, responseHandler.getBookingLastNameFromResponse(response),
                Messages.UPDATE_EXPECTED_LAST_NAME);
        assertEquals(JsonProperties.Values.UPDATED_TOTALPRICE,
                responseHandler.getBookingTotalPriceFromResponse(response), Messages.UPDATE_EXPECTED_TOTAL_PRICE);
        assertEquals(JsonProperties.Values.UPDATED_DEPOSITPAID, responseHandler.getBookingDepositPaidFromResponse(response),
                Messages.UPDATE_EXPECTED_DEPOSITPAID);
        assertEquals(JsonProperties.Values.UPDATED_ADDITIONAL_NEEDS, responseHandler.getBookingAdditionalNeedsFromResponse(response),
                Messages.UPDATE_EXPECTED_ADDITIONAL_NEEDS);
        assertEquals(JsonProperties.Values.UPDATED_CHECKIN, responseHandler.getBookingCheckInFromResponse(response),
                Messages.UPDATE_EXPECTED_CHECKIN);
        assertEquals(JsonProperties.Values.UPDATED_CHECKOUT, responseHandler.getBookingCheckOutFromResponse(response),
                Messages.UPDATE_EXPECTED_CHECKOUT);
    }
}
