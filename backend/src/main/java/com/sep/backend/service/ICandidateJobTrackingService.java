package com.sep.backend.service;


import com.sep.backend.models.CandidateTrackJob;

/**
 * The interface Candidate service.
 */
public interface ICandidateJobTrackingService {



CandidateTrackJob findByEmailAddress(String emailAddress);


}
