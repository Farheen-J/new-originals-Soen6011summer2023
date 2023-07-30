package com.sep.backend.service;

import com.sep.backend.dto.CandidateJobTrackingRequestDto;
import com.sep.backend.exception.TrackCandidateApplicationException;
import com.sep.backend.models.TrackCandidateApplication;
import org.springframework.stereotype.Service;

@Service
public interface ITrackCandidateApplicationService {
    TrackCandidateApplication saveApplication(CandidateJobTrackingRequestDto candidateJobTrackingRequestDto) throws TrackCandidateApplicationException;
}
