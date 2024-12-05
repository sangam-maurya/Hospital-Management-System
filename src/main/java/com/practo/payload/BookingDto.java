package com.practo.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDto {

    private long doctorId;

    private long patientId;

    private String bookingTime;

    private LocalDate bookingDate;
}
