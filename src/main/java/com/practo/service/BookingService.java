package com.practo.service;
import com.practo.entity.Booking;
import com.practo.entity.Doctor;
import com.practo.entity.Patient;
import com.practo.repositry.BookingRepository;
import com.practo.repositry.DoctorRepository;
import com.practo.repositry.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    private final List<String> availableTimeSlots = List.of("10:15 AM", "11:15 AM", "12:15 PM");

    /**
     * Creates a booking for a patient with a doctor.
     */
    public String createBooking(long doctorId, long patientId, String bookingTime, LocalDate bookingDate) {
        // Fetch the doctor and patient from the database
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor with ID " + doctorId + " not found."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        // Check if the slot is available
        List<Booking> existingBookings = bookingRepository.findByDoctorIdAndBookingDate(doctorId, bookingDate);
        boolean isSlotTaken = existingBookings.stream()
                .anyMatch(booking -> booking.getBookingTime().equals(bookingTime));

        if (isSlotTaken) {
            // Suggest the next available slot
            String nextAvailableSlot = getNextAvailableSlot(existingBookings);
            return "Slot already booked. Please book the next available slot at: " + nextAvailableSlot;
        }

        // Create and save the booking
        Booking booking = new Booking();
        booking.setDoctorId(doctor.getId());
        booking.setPatient(patient.getId());
        booking.setBookingTime(bookingTime);
        booking.setBookingDate(bookingDate);
        bookingRepository.save(booking);

        return "Booking confirmed for time slot: " + bookingTime;
    }

    /**
     * Gets the next available slot for the day.
     */
    private String getNextAvailableSlot(List<Booking> existingBookings) {
        for (String slot : availableTimeSlots) {
            boolean isTaken = existingBookings.stream()
                    .anyMatch(booking -> booking.getBookingTime().equals(slot));
            if (!isTaken) {
                return slot;
            }
        }
        return "No slots available today.";
    }

    /**
     * Refreshes the slots daily by clearing bookings for the previous day.
     */
    public void refreshDailyBookings() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        bookingRepository.deleteAll(bookingRepository.findByBookingDate(yesterday));
    }
}