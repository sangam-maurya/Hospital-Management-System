package com.practo.repositry;

import com.practo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDoctorIdAndBookingDate(long doctorId, LocalDate bookingDate);

    List<Booking> findByBookingDate(LocalDate bookingDate);
}