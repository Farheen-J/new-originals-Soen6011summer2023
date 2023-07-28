package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Blob;

/**
 * The type Candidate registration request dto.
 */
@Builder
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadResumeRequest {


@JsonProperty("email_address")
private String emailAddress;

@JsonProperty("uploaded_resume")
private byte[] uploadedResume;

}
