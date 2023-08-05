package com.sep.backend.service;


import com.sep.backend.models.EmployerTrackJob;

/**
 * The interface Candidate Job Tracking service.
 */
public interface IEmployerJobTrackingService {



EmployerTrackJob findByEmailAddress(String emailAddress);


}
