package com.automation.model;

import lombok.Data;

@Data
public class BookingRequestModel {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @Data
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }

    public static BookingRequestModel createBookingRequestModel() {
        BookingRequestModel booking = new BookingRequestModel();

        booking.setFirstname("Milos");
        booking.setLastname("Stamatovic");
        booking.setTotalprice(31415);
        booking.setDepositpaid(true);

        BookingRequestModel.BookingDates dates = new BookingRequestModel.BookingDates();
        dates.setCheckin("2025-09-27");
        dates.setCheckout("2025-10-05");
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("Room service");

        return booking;
    }

    public static BookingRequestModel updateBookingRequestModel() {
        BookingRequestModel updateBooking = new BookingRequestModel();

        updateBooking.setFirstname("Masa");
        updateBooking.setLastname("Spasic");
        updateBooking.setTotalprice(7000);
        updateBooking.setDepositpaid(true);

        BookingRequestModel.BookingDates dates = new BookingRequestModel.BookingDates();
        dates.setCheckin("2025-11-11");
        dates.setCheckout("2025-11-25");
        updateBooking.setBookingdates(dates);
        updateBooking.setAdditionalneeds("vegan meals");

        return updateBooking;


    }
}
