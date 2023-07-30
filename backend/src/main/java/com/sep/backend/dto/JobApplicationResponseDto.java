package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.enums.ApplicationStatus;
import lombok.Builder;

/**
 * The type Job Listing response dto.
 */
@Builder
public class JobApplicationResponseDto {

    @JsonProperty("job_id")
    private Integer jobId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("application_status")
    private String applicationStatus;

}
