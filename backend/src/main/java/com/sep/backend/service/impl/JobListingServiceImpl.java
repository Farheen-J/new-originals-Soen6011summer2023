package com.sep.backend.service.impl;


import com.sep.backend.dto.JobListingRequestDto;
import com.sep.backend.exception.JobListingRegistrationException;
import com.sep.backend.models.JobListing;
import com.sep.backend.repository.JobListingRepository;
import com.sep.backend.service.IJobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * The type Job Listing service.
 */
@Service
public class JobListingServiceImpl implements IJobListingService {

    private JobListingRepository jobListingRepository;


    /**
     * Instantiates a new Job Listing service.
     *
     * @param jobListingRepository the job listing repository
     */
    @Autowired
    public JobListingServiceImpl(JobListingRepository jobListingRepository) {
        this.jobListingRepository = jobListingRepository;

    }

    @Override
    public JobListing saveJobListing(JobListingRequestDto jobListingRequestDto) throws JobListingRegistrationException {

        if (Objects.isNull(jobListingRequestDto.getEmployerEmail())){
            throw new JobListingRegistrationException("Email Address is required");
        }

        JobListing jobListing = JobListing.builder()
                .company(jobListingRequestDto.getCompany())
                .tools(jobListingRequestDto.getTools())
                .languages(jobListingRequestDto.getLanguages())
                .position(jobListingRequestDto.getPosition())
                .role(jobListingRequestDto.getRole())
                .level(jobListingRequestDto.getLevel())
                .postedAt(jobListingRequestDto.getPostedAt())
                .contract(jobListingRequestDto.getContract())
                .location(jobListingRequestDto.getLocation())
                .salary(jobListingRequestDto.getSalary())
                .employerEmail(jobListingRequestDto.getEmployerEmail())
                .description(jobListingRequestDto.getDescription())
                .requirements(jobListingRequestDto.getRequirements()).build();

        jobListingRepository.save(jobListing);
        return jobListing;
    }

    @Override
    public JobListing getJobListingById(Integer id) {
        // Implement the logic to fetch the job listing from the database using the provided ID
        // Return the JobListing entity or null if not found
        return jobListingRepository.findById(id).orElse(null);
    }
    @Override
    public List<JobListing> getAllJobListings() {
        return jobListingRepository.findAll();
    }

}