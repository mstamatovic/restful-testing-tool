package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.config.ConfigReader;
import com.automation.constants.Messages;
import com.automation.model.BookingRequestModel;
import com.automation.requests.BookingRequests;
import com.automation.response.ResponseHandler;
import com.automation.response.ResponseValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PartialUpdateBookingTest extends BaseTest {

    private static int bookingId;

    @Test
    @Order(1)
    public void createBookingForUpdateTest() {
        BookingRequests createBookingRequest = new BookingRequests();
        ResponseHandler responseHandler = new ResponseHandler();

        Response createBookingResponse = createBookingRequest
                .createBooking(BookingRequestModel.createBookingRequestModel());

        bookingId = responseHandler.getBookingIdFromResponse(createBookingResponse);
    }

    @Test
    @Order(2)
    public void partialUpdateBookingTest() {
        BookingRequests partialUpdateBookingRequest = new BookingRequests();
        BookingRequestModel partialUpdateBookingModel = new BookingRequestModel();
        partialUpdateBookingModel.setTotalprice(7000);

        String authToken = partialUpdateBookingRequest
                .createAuthToken(ConfigReader.getUsername(), ConfigReader.getPassword());
        assertNotNull(authToken, Messages.INVALID_TOKEN);

        Response partialUpdatepdateResponse = partialUpdateBookingRequest
                .partialUpdateBooking(authToken, bookingId, partialUpdateBookingModel);

        ResponseValidator.verifyPartialUpdateBookingIsSuccessful(partialUpdatepdateResponse);
        ResponseValidator.verifyBookingIsPartiallyUpdated(partialUpdatepdateResponse);
    }
}
