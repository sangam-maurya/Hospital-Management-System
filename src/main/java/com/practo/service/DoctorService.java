package com.practo.service;

import com.practo.entity.Doctor;
import com.practo.payload.DoctorDto;
import com.practo.repositry.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper mapper;

    public Doctor saveDoctor(Doctor doctor){
        Doctor save = doctorRepository.save(doctor);
      return save;
    }

    public Doctor searchDoctor(String search){
        Doctor doctor = doctorRepository.searchByNameOrSpecialization(search).orElseThrow(()-> new NoSuchElementException("" +
                "Doctor is not preset " + search));
        return doctor;
    }
}
