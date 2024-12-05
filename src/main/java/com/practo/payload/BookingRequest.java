package com.practo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private long doctorId;
    private long patientId;
    private String bookingTime;
    private String bookingDate;
}