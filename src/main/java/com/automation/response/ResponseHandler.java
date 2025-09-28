package com.automation.response;

import com.automation.constants.JsonProperties;
import io.restassured.response.Response;

public class ResponseHandler {

    public int getBookingIdFromResponse(Response response) {
        return response.jsonPath().getInt(JsonProperties.Keys.BOOKING_ID);
    }

    public String getBookingFirstNameFromResponse(Response response) {
        return response.jsonPath().getString(JsonProperties.Keys.FIRST_NAME);
    }

    public String getBookingLastNameFromResponse(Response response) {
        return response.jsonPath().getString(JsonProperties.Keys.LAST_NAME);
    }

    public int getBookingTotalPriceFromResponse(Response response) {
        return response.jsonPath().getInt(JsonProperties.Keys.TOTAL_PRICE);
    }

    public boolean getBookingDepositPaidFromResponse(Response response) {
        return response.jsonPath().getBoolean(JsonProperties.Keys.DEPOSITPAID);
    }

    public String getBookingAdditionalNeedsFromResponse(Response response) {
        return response.jsonPath().getString(JsonProperties.Keys.ADDITIONAL_NEEDS);
    }

    public String getBookingCheckInFromResponse(Response response) {
        return response.jsonPath().getString(JsonProperties.Keys.CHECKIN);
    }

    public String getBookingCheckOutFromResponse(Response response) {
        return response.jsonPath().getString(JsonProperties.Keys.CHECKOUT);
    }
}
