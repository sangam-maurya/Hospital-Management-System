package com.practo.controller;

import com.practo.entity.Review;
import com.practo.payload.DoctorDto;
import com.practo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/add")
    public Review createReview (@RequestBody Review review ,
                                @RequestParam long patientId ,
                                @RequestParam long doctorId){
        Review review1 = reviewService.createReview(review, patientId, doctorId);
        return review1;
    }
    @GetMapping
    public DoctorDto getReviewByDoctorId(@RequestParam  long doctorId){
        DoctorDto reviewByDoctorId = reviewService.getReviewByDoctorId(doctorId);
        return reviewByDoctorId;
    }
}
