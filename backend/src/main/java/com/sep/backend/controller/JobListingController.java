package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.*;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.exception.JobListingRegistrationException;
import com.sep.backend.models.JobApplication;
import com.sep.backend.models.JobListing;
import com.sep.backend.service.IJobApplicationService;
import com.sep.backend.service.IJobListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Job Listing controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class JobListingController {

    private final IJobListingService iJobListingService;
    private final IJobApplicationService iJobApplicationService;
    /**
     * Instantiates a new Job Listing controller.
     *
     * @param iJobListingService the job listing service
     */
    @Autowired
    public JobListingController(IJobListingService iJobListingService, IJobApplicationService iJobApplicationService)
    {
        this.iJobListingService = iJobListingService;
        this.iJobApplicationService = iJobApplicationService;
    }

    /**
     * Register job listing response dto.
     *
     * @param jobListingRequestDto the job listing request dto
     * @return the response dto
     */
    @RequestMapping(method = RequestMethod.POST, value = UriConstants.REGISTER_JOB_LISTING)
    public ResponseDto<JobListingResponseDto> addJobListing(@RequestBody JobListingRequestDto jobListingRequestDto){
        JobListing jobListing;
        try{
            jobListing = iJobListingService.saveJobListing(jobListingRequestDto);
        } catch (JobListingRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                JobListingResponseDto.builder()
                        .id(jobListing.getId())
                        .company(jobListing.getCompany())
                        .isNew(jobListing.isNew())
                        .isFeatured(jobListing.isFeatured())
                        .position(jobListing.getPosition())
                        .role(jobListing.getRole())
                        .level(jobListing.getLevel())
                        .postedAt(jobListing.getPostedAt())
                        .contract(jobListing.getContract())
                        .location(jobListing.getLocation())
                        .employerEmail(jobListing.getEmployerEmail())
                        .description(jobListing.getDescription())
                        .requirements(jobListing.getRequirements())
                        .build()
        );
    }


    /**
     * Get job listing by ID.
     *
     * @param id the ID of the job listing to retrieve
     * @return the response DTO containing the job listing data
     */
    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_JOB_LISTING)
    public ResponseDto<JobListingResponseDto> getJobListingById(@RequestParam (name = "id") Integer id) {
        JobListing jobListing;
        try {
            jobListing = iJobListingService.getJobListingById(id);

            // Check if the job listing exists in the database
            if (jobListing == null) {
                return new ResponseDto<>(Collections.singletonList("Job listing not found"));
            }

            // Create the response DTO containing the job listing data
            JobListingResponseDto responseDto = JobListingResponseDto.builder()
                    .id(jobListing.getId())
                    .company(jobListing.getCompany())
                    .isNew(jobListing.isNew())
                    .isFeatured(jobListing.isFeatured())
                    .position(jobListing.getPosition())
                    .role(jobListing.getRole())
                    .level(jobListing.getLevel())
                    .postedAt(jobListing.getPostedAt())
                    .contract(jobListing.getContract())
                    .location(jobListing.getLocation())
                    .employerEmail(jobListing.getEmployerEmail())
                    .description(jobListing.getDescription())
                    .requirements(jobListing.getRequirements())
                    .build();

            return new ResponseDto<>(responseDto);
        } catch (Exception e) {
            log.error("Error occurred while fetching job listing: ", e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }
    /**
     * Get all job listings.
     *
     * @return the list of all job listings
     */
    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_JOB_LISTINGS)
    public List<JobListingResponseDto> getAllJobListings() {
        List<JobListing> jobListings = iJobListingService.getAllJobListings();
        List<JobListingResponseDto> responseDtoList = new ArrayList<>();

        for (JobListing jobListing : jobListings) {
            JobListingResponseDto responseDto = JobListingResponseDto.builder()
                    .id(jobListing.getId())
                    .company(jobListing.getCompany())
                    .isNew(jobListing.isNew())
                    .isFeatured(jobListing.isFeatured())
                    .position(jobListing.getPosition())
                    .role(jobListing.getRole())
                    .level(jobListing.getLevel())
                    .postedAt(jobListing.getPostedAt())
                    .contract(jobListing.getContract())
                    .location(jobListing.getLocation())
                    .employerEmail(jobListing.getEmployerEmail())
                    .description(jobListing.getDescription())
                    .requirements(jobListing.getRequirements())
                    .build();

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = UriConstants.CANDIDATE_APPLY_JOB)
    public ResponseDto<JobApplicationResponseDto> applyForJob(@RequestBody JobApplicationRequestDto jobApplicationRequestDto){
        JobApplication jobApplication;
        try{
            jobApplication = iJobApplicationService.saveJobApplication(jobApplicationRequestDto);
        } catch (JobApplicationRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                JobApplicationResponseDto.builder()
                        .jobId(jobApplication.getJobID())
                        .name(jobApplication.getName())
                        .emailAddress(jobApplication.getEmailAddress())
                        .phoneNumber(jobApplication.getPhoneNumber())
                        .applicationStatus(String.valueOf(jobApplication.getApplicationStatus()))
                        .build()
        );
    }
}
