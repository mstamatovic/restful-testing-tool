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
public class UpdateBookingTest extends BaseTest {

    public static int bookingId;

    @Test
    @Order(1)
    public void createBookingForUpdateTest() {
        BookingRequests createBookingRequest = new BookingRequests();
        ResponseHandler responseHandler = new ResponseHandler();

        Response createBookingResponse = createBookingRequest.createBooking(BookingRequestModel.createBookingRequestModel());

        bookingId = responseHandler.getBookingIdFromResponse(createBookingResponse);
    }

    @Test
    @Order(2)
    public void updateBookingTest() {
        BookingRequests updateBookingRequest = new BookingRequests();
        BookingRequestModel updateBookingModel = BookingRequestModel.updateBookingRequestModel();

        String authToken = updateBookingRequest.createAuthToken(ConfigReader.getUsername(), ConfigReader.getPassword());
        assertNotNull(authToken, Messages.INVALID_TOKEN);

        Response updateBookingResponse = updateBookingRequest.updateBooking(authToken, bookingId, updateBookingModel);

        ResponseValidator.verifyUpdateBookingIsSuccessful(updateBookingResponse);
        ResponseValidator.verifyBookingIsUpdated(updateBookingResponse);
    }
}
