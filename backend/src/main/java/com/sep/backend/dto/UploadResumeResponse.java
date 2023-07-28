package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.sql.Blob;

/**
 * The type Build Resume response dto.
 */
@Builder
public class UploadResumeResponse {

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("uploaded_resume")
    private byte[] uploadedResume;


}
