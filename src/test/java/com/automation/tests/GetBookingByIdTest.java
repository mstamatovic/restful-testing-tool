package com.automation.tests;

import com.automation.base.BaseTest;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetBookingByIdTest extends BaseTest {

    public static int bookingId;

    @Test
    @Order(1)
    public void createBookingToGetId() {

        BookingRequests bookingRequest = new BookingRequests();
        ResponseHandler responseHandler = new ResponseHandler();

        Response createBookingResponse = bookingRequest.createBooking(BookingRequestModel.createBookingRequestModel());

        bookingId = responseHandler.getBookingIdFromResponse(createBookingResponse);


    }

    @Test
    @Order(2)
    public void getBookingByIdTest() {

        BookingRequests getBookingById = new BookingRequests();

        Response getBookingByIdResponse = getBookingById.getBookingById(bookingId);

        ResponseValidator.verifyGetBookingByIdIsFound(bookingId, getBookingByIdResponse);
    }
}
