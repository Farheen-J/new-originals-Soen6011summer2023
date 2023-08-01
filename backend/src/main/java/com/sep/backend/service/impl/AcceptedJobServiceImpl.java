package com.sep.backend.service.impl;


import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.AcceptedJob;
import com.sep.backend.repository.AcceptedJobRepository;
import com.sep.backend.service.IAcceptedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.sep.backend.enums.ApplicationStatus.ACCEPTED_BY_EMPLOYER;

/**
 * The type Job Application service.
 */
@Service
public class AcceptedJobServiceImpl implements IAcceptedJobService {

    private AcceptedJobRepository acceptedJobRepository;


    /**
     * Instantiates a new Job Application service.
     *
     * @param acceptedJobRepository the job application repository
     */
    @Autowired
    public AcceptedJobServiceImpl(AcceptedJobRepository acceptedJobRepository) {
        this.acceptedJobRepository = acceptedJobRepository;

    }

    @Override
    public AcceptedJob saveAcceptCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException {

        if (Objects.isNull(jobApplicationRequestDto.getEmailAddress()))
        {
            throw new JobApplicationRegistrationException("Email Address is required");
        }
        if (Objects.isNull(jobApplicationRequestDto.getJobId()))
        {
            throw new JobApplicationRegistrationException("Job ID is required");
        }

        AcceptedJob acceptedJob = AcceptedJob.builder()
                .jobID(jobApplicationRequestDto.getJobId())
                .emailAddress(jobApplicationRequestDto.getEmailAddress())
                .applicationStatus(ACCEPTED_BY_EMPLOYER)
                .build();

        acceptedJobRepository.save(acceptedJob);
        return acceptedJob;
    }

    @Override
    public AcceptedJob getAcceptedJobByEmailAddress(String emailAddress)
    {
        return acceptedJobRepository.findFirstByEmailAddress(emailAddress);
    }
}