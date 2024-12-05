package com.practo.controller;

import com.practo.entity.Patient;
import com.practo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/add")
    public Patient savePatient(@RequestBody Patient patient){
        Patient patient1 = patientService.createPatient(patient);
        return patient1;
    }
}
