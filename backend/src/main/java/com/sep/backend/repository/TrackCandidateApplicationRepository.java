package com.sep.backend.repository;


import com.sep.backend.enums.ApplicationStatus;
import com.sep.backend.models.JobListing;
import com.sep.backend.models.TrackCandidateApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackCandidateApplicationRepository extends JpaRepository<TrackCandidateApplication, Integer> {
    /**
     * Find first by email address track application.
     *
     * @param employerEmail the email address
     * @return the track candidate application
     */
    TrackCandidateApplication findFirstByEmployerEmail(String employerEmail);

//    TrackCandidateApplication findFirstByApplicationStatus(ApplicationStatus applicationStatus);
}
