package com.automation.tests;

import com.automation.config.ConfigReader;
import com.automation.model.BookingRequestModel;
import com.automation.requests.BookingRequests;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteBookingInvalidTokenTest {

    public static int bookingId;
    public static Response createResponse;
    public static BookingRequests bookingService = new BookingRequests();

    @Test
    @Order(1)
    public void createBookingBeforeDeleteTest() {
        // 1. Kreiraj booking (da imamo validan ID)
        BookingRequestModel booking = new BookingRequestModel();
        booking.setFirstname("ToDelete");
        booking.setLastname("JUnit");
        booking.setTotalprice(200);
        booking.setDepositpaid(false);

        BookingRequestModel.BookingDates dates = new BookingRequestModel.BookingDates();
        dates.setCheckin("2025-01-01");
        dates.setCheckout("2025-01-05");
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("Lunch");

        BookingRequests service = new BookingRequests();

        createResponse = service.createBooking(booking);
        bookingId = createResponse.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        createResponse.then().statusCode(200);


    }

    @Test
    @Order(2)
    public void deleteBookingInvalidTokenTest() {
        bookingId = createResponse.jsonPath().getInt("bookingid");
        assertTrue(bookingId > 0, "Booking ID mora biti validan");

        Response deleteResponse = bookingService.deleteBooking(bookingId, " invalid_token");
        System.out.println(deleteResponse.asString());
        deleteResponse
                .then()
                .statusCode(403);
        assertTrue(deleteResponse.asString().contains("Forbidden"));
    }
}
