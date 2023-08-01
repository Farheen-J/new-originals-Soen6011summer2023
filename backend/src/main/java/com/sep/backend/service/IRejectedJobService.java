package com.sep.backend.service;

import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.RejectedJob;

/**
 * The interface Job Application service.
 */
public interface IRejectedJobService {

    /**
     * Save job application.
     *
     * @param jobApplicationRequestDto the job application request dto
     * @return the resume
     */
    RejectedJob saveRejectCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException;

    RejectedJob getRejectedJobByEmailAddress(String emailAddress);
}
