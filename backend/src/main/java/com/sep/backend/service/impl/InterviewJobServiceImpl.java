package com.sep.backend.service.impl;


import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.InterviewJob;
import com.sep.backend.repository.InterviewJobRepository;
import com.sep.backend.service.IInterviewJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.sep.backend.enums.ApplicationStatus.INTERVIEW_WITH_EMPLOYER;

/**
 * The type Job Application service.
 */
@Service
public class InterviewJobServiceImpl implements IInterviewJobService {

    private InterviewJobRepository interviewJobRepository;


    /**
     * Instantiates a new Job Application service.
     *
     * @param acceptedJobRepository the job application repository
     */
    @Autowired
    public InterviewJobServiceImpl(InterviewJobRepository interviewJobRepository) {
        this.interviewJobRepository = interviewJobRepository;

    }

    @Override
    public InterviewJob saveInterviewCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException {

        if (Objects.isNull(jobApplicationRequestDto.getEmailAddress()))
        {
            throw new JobApplicationRegistrationException("Email Address is required");
        }
        if (Objects.isNull(jobApplicationRequestDto.getJobId()))
        {
            throw new JobApplicationRegistrationException("Job ID is required");
        }

        InterviewJob interviewJob = InterviewJob.builder()
                .jobID(jobApplicationRequestDto.getJobId())
                .emailAddress(jobApplicationRequestDto.getEmailAddress())
                .applicationStatus(INTERVIEW_WITH_EMPLOYER)
                .build();

        interviewJobRepository.save(interviewJob);
        return interviewJob;
    }

    @Override
    public InterviewJob getInterviewJobByEmailAddress(String emailAddress)
    {
        return interviewJobRepository.findFirstByEmailAddress(emailAddress);
    }
}