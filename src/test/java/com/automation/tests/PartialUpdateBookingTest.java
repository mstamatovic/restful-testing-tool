package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.config.ConfigReader;
import com.automation.model.BookingRequestModel;
import com.automation.requests.BookingRequests;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PartialUpdateBookingTest extends BaseTest {

    public static int bookingId;

    @Test
    @Order(1)
    public void createBookingForUpdateTest() {

        BookingRequests bookingService = new BookingRequests();
        // 1. Kreiraj booking (da imamo validan ID)
        BookingRequestModel booking = new BookingRequestModel();
        booking.setFirstname("Mina");
        booking.setLastname("Spasic");
        booking.setTotalprice(6000);
        booking.setDepositpaid(false);

        BookingRequestModel.BookingDates dates = new BookingRequestModel.BookingDates();
        dates.setCheckin("2025-01-01");
        dates.setCheckout("2025-01-05");
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("room service");

        BookingRequests service = new BookingRequests();
        Response createBookingResponse;
        createBookingResponse = service.createBooking(booking);
        bookingId = createBookingResponse.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        createBookingResponse.then().statusCode(200);


    }

    @Test
    @Order(2)
    public void partialUpdateBookingTest() {

        BookingRequests bookingService = new BookingRequests();
        BookingRequestModel updateBooking = new BookingRequestModel();
        updateBooking.setTotalprice(7000);

        String authToken = bookingService.createAuthToken(ConfigReader.getUsername(), ConfigReader.getPassword());
        assertNotNull(authToken, "Token ne sme biti null");

        Response updateResponse = bookingService.partialUpdateBooking(authToken, bookingId, updateBooking);
        updateResponse.then().statusCode(200);

        int totalprice = updateResponse.jsonPath().getInt("totalprice");

        assertEquals(totalprice, updateResponse.jsonPath().getInt("totalprice"), "Totalprice should be the same");
    }
}
