package com.sep.backend.service.impl;

import com.sep.backend.dto.CandidateJobTrackingRequestDto;
import com.sep.backend.exception.JobListingRegistrationException;
import com.sep.backend.exception.TrackCandidateApplicationException;
import com.sep.backend.models.JobListing;
import com.sep.backend.models.TrackCandidateApplication;
import com.sep.backend.repository.JobListingRepository;
import com.sep.backend.repository.TrackCandidateApplicationRepository;
import com.sep.backend.service.ITrackCandidateApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class TrackCandidateApplicationImpl implements ITrackCandidateApplicationService {
    private  TrackCandidateApplicationRepository trackCandidateApplicationRepository;

    @Autowired
    public TrackCandidateApplicationImpl(TrackCandidateApplicationRepository trackCandidateApplicationRepository) {
        this.trackCandidateApplicationRepository = trackCandidateApplicationRepository;

    }
    @Override
    public TrackCandidateApplication saveApplication(CandidateJobTrackingRequestDto candidateJobTrackingRequestDto) throws TrackCandidateApplicationException {
        if (Objects.isNull(candidateJobTrackingRequestDto.getEmployerEmail())){
            throw new TrackCandidateApplicationException("Email Address is required");
        }

        TrackCandidateApplication trackCandidateApplication = TrackCandidateApplication.builder()
                .jobId(candidateJobTrackingRequestDto.getJobId())
                .company(candidateJobTrackingRequestDto.getCompany())
                .isNew(candidateJobTrackingRequestDto.isNew())
                .isFeatured(candidateJobTrackingRequestDto.isFeatured())
                .position(candidateJobTrackingRequestDto.getPosition())
                .role(candidateJobTrackingRequestDto.getRole())
                .level(candidateJobTrackingRequestDto.getLevel())
                .postedAt(candidateJobTrackingRequestDto.getPostedAt())
                .contract(candidateJobTrackingRequestDto.getContract())
                .location(candidateJobTrackingRequestDto.getLocation())
                .employerEmail(candidateJobTrackingRequestDto.getEmployerEmail())
                .description(candidateJobTrackingRequestDto.getDescription())
                .requirements(candidateJobTrackingRequestDto.getRequirements())
                .applicationStatus(candidateJobTrackingRequestDto.getApplicationStatus())
                .build();

        trackCandidateApplicationRepository.save(trackCandidateApplication);
        return trackCandidateApplication;
    }
}
