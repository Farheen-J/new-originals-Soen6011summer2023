package com.sep.backend.service;

import com.sep.backend.dto.BuildResumeRequestDto;
import com.sep.backend.exception.BuildResumeException;
import com.sep.backend.models.BuildResume;

import java.util.List;

/**
 * The interface Patient service.
 */
public interface IBuildResumeService {

    /**
     * Save doctor.
     *
     * @param buildResumeRequestdto the patient registration request dto
     * @return the patient
     */
    BuildResume saveResume(BuildResumeRequestDto buildResumeRequestdto) throws BuildResumeException;

//    LoginResponse getLoginDetails(String email, String password) throws LoginException;

    List<BuildResume> getResume();

//    void deleteDoctor(String emailAddress);
//
//    Doctor findById(Integer id);
}
