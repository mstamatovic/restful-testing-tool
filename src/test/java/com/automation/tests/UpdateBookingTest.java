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
public class UpdateBookingTest extends BaseTest {

    public static int bookingId;

    @Test
    @Order(1)
    public void createBookingForUpdateTest() {

        BookingRequests bookingService = new BookingRequests();
        // 1. Kreiraj booking (da imamo validan ID)
        BookingRequestModel booking = new BookingRequestModel();
        booking.setFirstname("Beba");
        booking.setLastname("Stamatovic");
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
    public void updateBookingTest() {

        BookingRequests bookingService = new BookingRequests();
        BookingRequestModel updateBooking = new BookingRequestModel();
        updateBooking.setFirstname("Masa");
        updateBooking.setLastname("Spasic");
        updateBooking.setTotalprice(7000);
        updateBooking.setDepositpaid(true);

        BookingRequestModel.BookingDates dates = new BookingRequestModel.BookingDates();
        dates.setCheckin("2025-02-02");
        dates.setCheckout("2025-03-06");
        updateBooking.setBookingdates(dates);
        updateBooking.setAdditionalneeds("vegan meals");

        String authToken = bookingService.createAuthToken(ConfigReader.getUsername(), ConfigReader.getPassword());
        assertNotNull(authToken, "Token ne sme biti null");

        Response updateResponse = bookingService.updateBooking(bookingId, updateBooking);
        System.out.println("*** " + bookingId + " ***");
        updateResponse.then().statusCode(200);

        String firstname = updateResponse.jsonPath().getString("firstname");
        String lastname = updateResponse.jsonPath().getString("lastname");
        System.out.println(firstname);

        assertEquals(bookingId, updateResponse.jsonPath().getInt("bookingid"), "Booking ID should be the same");
        assertEquals("Masa", firstname, "Firstname should be Isidora");
        assertEquals("Spasic", lastname, "Lastname should be Spasic");
    }
}
