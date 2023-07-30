package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateJobTrackingRequestDto {
    @JsonProperty("jobId")
    private String jobId;

    @JsonProperty( "company")
    private String company;

    @JsonProperty( "isNew")
    private boolean isNew;

    @JsonProperty( "isFeatured")
    private boolean isFeatured;

    @JsonProperty( "position")
    private String position;

    @JsonProperty("role")
    private String role;

    @JsonProperty( "level")
    private String level;

    @JsonProperty( "posted_at")
    private Date postedAt;

    @JsonProperty( "location")
    private String location;

    @JsonProperty( "contract")
    private String contract;

    @JsonProperty("employer_email")
    private String employerEmail;

    @JsonProperty("description")
    private String description;

    @JsonProperty( "requirements")
    private String requirements;

    @JsonProperty( "application_status")
    private ApplicationStatus applicationStatus;
}
