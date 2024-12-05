package com.practo.controller;

import com.practo.entity.Doctor;
import com.practo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping("/add")
    public ResponseEntity<Doctor> createDoc(@RequestBody Doctor doctor){
        Doctor doctor1 = doctorService.saveDoctor(doctor);
        return new ResponseEntity<>(doctor1, HttpStatus.CREATED);
    }

    @GetMapping
    public Doctor searchDoctor(@RequestParam String search){
        Doctor doctor = doctorService.searchDoctor(search);
        return doctor;
    }
}
