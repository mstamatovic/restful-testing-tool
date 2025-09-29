package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.model.BookingRequestModel;
import com.automation.requests.BookingRequests;
import com.automation.response.ResponseHandler;
import com.automation.response.ResponseValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class CreateBookingTest extends BaseTest {


    @Test
    public void createBookingTest() {

        BookingRequests createBookingRequest = new BookingRequests();
        ResponseHandler responseHandler = new ResponseHandler();

        Response createBookingResponse = createBookingRequest
                .createBooking(BookingRequestModel.createBookingRequestModel());

        ResponseValidator
                .verifyBookingIdIsValid(responseHandler.getBookingIdFromResponse(createBookingResponse),
                        createBookingResponse);
    }
}
