package com.sep.backend.models;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.sep.backend.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * The type Job Application.
 */
@Entity
@Table(name = "track_candidate_application")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackCandidateApplication extends BaseModel{
    @Column(name = "jobId", nullable = false)
    private String jobId;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "isNew")
    private boolean isNew;

    @Column(name = "isFeatured")
    private boolean isFeatured;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "role")
    private String role;

    @Column(name = "level")
    private String level;

    @Column(name = "posted_at")
    private Date postedAt;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "contract", nullable = false)
    private String contract;

    @Column(name = "employer_email", nullable = false)
    private String employerEmail;

    @Column(name = "description")
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "application_status")
    private ApplicationStatus applicationStatus;
    }

