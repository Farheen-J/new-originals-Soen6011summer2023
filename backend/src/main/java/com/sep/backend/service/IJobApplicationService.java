package com.sep.backend.service;

import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.JobApplication;

/**
 * The interface Job Application service.
 */
public interface IJobApplicationService {

    /**
     * Save job application.
     *
     * @param jobApplicationRequestDto the job application request dto
     * @return the resume
     */
    JobApplication saveJobApplication(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException;


}
