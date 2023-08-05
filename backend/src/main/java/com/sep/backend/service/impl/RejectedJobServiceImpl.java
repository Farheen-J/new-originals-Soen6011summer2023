package com.sep.backend.service.impl;


import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.RejectedJob;
import com.sep.backend.repository.RejectedJobRepository;
import com.sep.backend.service.IRejectedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.sep.backend.enums.ApplicationStatus.REJECTED_BY_EMPLOYER;

/**
 * The type Rejected Job service.
 */
@Service
public class RejectedJobServiceImpl implements IRejectedJobService {

    private RejectedJobRepository rejectedJobRepository;


    /**
     * Instantiates a new Rejected Job service.
     *
     * @param rejectedJobRepository the rejected job repository
     */
    @Autowired
    public RejectedJobServiceImpl(RejectedJobRepository rejectedJobRepository) {
        this.rejectedJobRepository = rejectedJobRepository;

    }

    @Override
    public RejectedJob saveRejectCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException {

        if (Objects.isNull(jobApplicationRequestDto.getEmailAddress()))
        {
            throw new JobApplicationRegistrationException("Email Address is required");
        }
        if (Objects.isNull(jobApplicationRequestDto.getJobId()))
        {
            throw new JobApplicationRegistrationException("Job ID is required");
        }

        RejectedJob rejectedJob = RejectedJob.builder()
                .jobID(jobApplicationRequestDto.getJobId())
                .emailAddress(jobApplicationRequestDto.getEmailAddress())
                .employerEmail(jobApplicationRequestDto.getEmployerEmail())
                .applicationStatus(REJECTED_BY_EMPLOYER)
                .build();

        rejectedJobRepository.save(rejectedJob);
        return rejectedJob;
    }

    @Override
    public RejectedJob getRejectedJobByEmailAddress(String emailAddress)
    {
        return rejectedJobRepository.findFirstByEmailAddress(emailAddress);
    }
}