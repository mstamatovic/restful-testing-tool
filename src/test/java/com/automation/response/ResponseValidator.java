package com.automation.response;

import com.automation.constants.JsonProperties;
import com.automation.constants.Messages;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseValidator {

    public static void verifyBookingIdIsValid(int bookingId, Response response) {

        response.then()
                .statusCode(200);
        assertTrue(bookingId > 0, Messages.INVALID_BOOKING_ID);

    }

    public static void verifyGetBookingById(int bookingId, Response response) {

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
                Messages.EXPECTED_FIRST_NAME);
        assertEquals(JsonProperties.Values.CHECKIN, responseHandler.getBookingCheckInFromResponse(response),
                Messages.EXPECTED_CHECKIN);
        assertEquals(JsonProperties.Values.CHECKOUT, responseHandler.getBookingCheckOutFromResponse(response),
                Messages.EXPECTED_CHECKOUT);

    }
}
