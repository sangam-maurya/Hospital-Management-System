package com.practo.service;

import com.practo.entity.Patient;
import com.practo.repositry.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient createPatient(Patient patient){
        Patient save = patientRepository.save(patient);
        return save;
    }

}

