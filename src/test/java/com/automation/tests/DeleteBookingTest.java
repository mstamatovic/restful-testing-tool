package com.automation.tests;

import com.automation.config.ConfigReader;
import com.automation.model.BookingRequestModel;
import com.automation.service.BookingService;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteBookingTest {

    public static int bookingId;
    public static Response createResponse;
    public static BookingService bookingService = new BookingService();

    @Test
    @Order(1)
    public void createBookingBeforeDelete() {
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

        BookingService service = new BookingService();

        createResponse = service.createBooking(booking);
        bookingId = createResponse.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        createResponse.then().statusCode(200);


    }

    @Test
    @Order(2)
    public void deleteBookingSuccessfully() {
        bookingId = createResponse.jsonPath().getInt("bookingid");
        assertTrue(bookingId > 0, "Booking ID mora biti validan");

        // 2. Dobij token
        String authToken = bookingService.createAuthToken(ConfigReader.getUsername(), ConfigReader.getPassword());
        assertNotNull(authToken, "Token ne sme biti null");

        // 3. Obriši booking
//        Response deleteResponse = bookingService.deleteBooking(bookingId);
        Response deleteResponse = bookingService.deleteBooking(bookingId, authToken);
        System.out.println(deleteResponse.asString());
        deleteResponse
                .then()
                .statusCode(201);
        assertTrue(deleteResponse.asString().contains("Created"));


        // 4. Proveri da više ne postoji
        Response getAfterDelete = bookingService.getBookingById(bookingId);
        getAfterDelete.then().statusCode(404);
    }

//    @Test
//    public void shouldReturn403WhenDeletingWithoutValidToken() {
//        // Kreiraj booking
//        Booking booking = new Booking();
//        booking.setFirstname("NoAuth");
//        booking.setLastname("Test");
//        booking.setTotalprice(100);
//        booking.setDepositpaid(true);
//
//        Booking.BookingDates dates = new Booking.BookingDates();
//        dates.setCheckin("2025-02-01");
//        dates.setCheckout("2025-02-03");
//        booking.setBookingdates(dates);
//
//        BookingService service = new BookingService();
//        Response createResponse = service.createBooking(booking);
//        createResponse.then().statusCode(200);
//        int bookingId = createResponse.jsonPath().getInt("bookingid");
//
//        // Pokušaj brisanje BEZ tokena (ili sa nevalidnim)
//        Response deleteResponse = service.deleteBooking(bookingId, "invalid_token_123");
//        deleteResponse.then().statusCode(403);
//    }
}