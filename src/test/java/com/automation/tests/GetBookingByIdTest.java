package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.model.BookingRequestModel;
import com.automation.service.BookingService;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetBookingByIdTest extends BaseTest {

    public static Response createResponse;
    public static int bookingId;

    @Test
    @Order(1)
    public void createBookingToGetId() {

        BookingService bookingService = new BookingService();
        // 1. Kreiraj booking (da imamo validan ID)
        BookingRequestModel booking = new BookingRequestModel();
        booking.setFirstname("Isidora");
        booking.setLastname("Stamatovic");
        booking.setTotalprice(5000);
        booking.setDepositpaid(false);

        BookingRequestModel.BookingDates dates = new BookingRequestModel.BookingDates();
        dates.setCheckin("2025-01-01");
        dates.setCheckout("2025-01-05");
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("Lunch");

        BookingService service = new BookingService();

        createResponse = service.createBooking(booking);
        bookingId = createResponse.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        createResponse.then().statusCode(200);


    }

    @Test
    @Order(2)
    public void getBookingByIdTest() {

        BookingService bookingService = new BookingService();

        Response response = bookingService.getBookingById(bookingId);
        response.then().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        System.out.println(firstname);
        assertEquals("Isidora", firstname, "Firstname should be Isidora");
    }
}
