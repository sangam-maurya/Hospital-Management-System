package com.practo.service;

import com.practo.entity.Doctor;
import com.practo.entity.Patient;
import com.practo.entity.Review;
import com.practo.payload.DoctorDto;
import com.practo.repositry.DoctorRepository;
import com.practo.repositry.PatientRepository;
import com.practo.repositry.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReviewRepository reviewRepository;

    public Review createReview(Review review , long patientId , long doctorId){
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("Patient with id " + patientId + " is not present.");
        }
        if (doctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor with id " + doctorId + " is not present.");
        }

           review.setDoctorId(doctorId);
           review.setPatientId(patientId);
           Review save = reviewRepository.save(review);
           return save;
    }
    public DoctorDto getReviewByDoctorId(long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->
                new NoSuchElementException("doctor id is not present"));
        List<Review> reviews = reviewRepository.findByDoctorId(doctorId);
        double totalRating = 0;
        for (Review review:reviews){
            totalRating+=review.getRating();
        }
        double averageRating = totalRating /reviews.size();
        double ratingPercentage = (averageRating /5.0) * 100.0;
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setDoctor(doctor);
        doctorDto.setReviews(reviews);
        doctorDto.setRatingPercentage(ratingPercentage);
        return doctorDto;
    }
}
