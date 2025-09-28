package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.config.ConfigReader;
import com.automation.model.BookingRequestModel;
import com.automation.service.BookingService;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateBookingTest extends BaseTest {

//    private static BookingService bookingService;
//    private static String authToken;

//    @BeforeAll
//    public static void setup() {
//        bookingService = new BookingService();
//        authToken = bookingService.createAuthToken("admin", "password123");
//    }

    public static BookingService bookingService;
    public static String authToken;


    @Test
    public void createBookingTest() {

        bookingService = new BookingService();

        BookingRequestModel booking = new BookingRequestModel();
        booking.setFirstname("Milos");
        booking.setLastname("Stamatovic");
        booking.setTotalprice(5000);
        booking.setDepositpaid(true);

        BookingRequestModel.BookingDates dates = new BookingRequestModel.BookingDates();
        dates.setCheckin("2025-09-27");
        dates.setCheckout("2024-10-05");
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("Room service");

        Response response = bookingService.createBooking(booking);
        response.then().statusCode(200);

        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        assertTrue(bookingId > 0, "Booking ID should be positive");
    }
}
