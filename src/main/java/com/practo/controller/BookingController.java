package com.practo.controller;

import com.practo.payload.BookingDto;
import com.practo.payload.BookingRequest;
import com.practo.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(
                request.getDoctorId(),
                request.getPatientId(),
                request.getBookingTime(),
                LocalDate.parse(request.getBookingDate()) // Convert String to LocalDate

        );
    }
    @PostMapping("/refresh")
    public String refreshBookings() {
        bookingService.refreshDailyBookings();
        return "Bookings refreshed for the day.";
    }
}
