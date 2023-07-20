package com.sep.backend.repository;

import com.sep.backend.enums.Gender;
import com.sep.backend.models.BuildResume;
//import com.sep.backend.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Counselor repository.
 */
public interface BuildResumeRepository extends JpaRepository<BuildResume, Integer> {



    /**
     * Find first by email address patient.
     *
     * @param emailAddress the email address
     * @return the patient
     */
    BuildResume findFirstByEmailAddress(String emailAddress);

    List<BuildResume> findAll();


}
