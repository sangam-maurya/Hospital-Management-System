package com.practo.payload;

import com.practo.entity.Doctor;
import com.practo.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorDto {
    private Doctor doctor;
    private List<Review> reviews;
    private double ratingPercentage;
}
