package com.practo.repositry;

import com.practo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.name LIKE %:search% OR d.specialization LIKE %:search%")
    Optional<Doctor> searchByNameOrSpecialization(@Param("search") String search);
}