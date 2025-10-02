package com.automation.tests;

import com.automation.constants.HeaderParamaters;
import com.automation.model.BookingRequestModel;
import com.automation.requests.BookingRequests;
import com.automation.response.ResponseHandler;
import com.automation.response.ResponseValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteBookingInvalidTokenTest {

    private static int bookingId;

    @Test
    @Order(1)
    public void createBookingBeforeDeleteTest() {
        BookingRequests createBookingRequest = new BookingRequests();
        ResponseHandler responseHandler = new ResponseHandler();

        Response createBookingResponse = createBookingRequest
                .createBooking(BookingRequestModel.createBookingRequestModel());

        bookingId = responseHandler.getBookingIdFromResponse(createBookingResponse);
    }

    @Test
    @Order(2)
    public void deleteBookingInvalidTokenTest() {
        BookingRequests deleteBookingRequest = new BookingRequests();

        Response deleteBookingResponse = deleteBookingRequest
                .deleteBooking(bookingId, HeaderParamaters.INVALID_TOKEN);

        ResponseValidator.verifyDeleteBookingWithInvalidTokenIsForbidden(deleteBookingResponse);
    }
}
