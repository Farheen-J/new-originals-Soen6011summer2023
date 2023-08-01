package com.sep.backend.models;

import com.sep.backend.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The type Rejected Job.
 */
@Entity
@Table(name = "rejected_job")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RejectedJob {

@Id
@Column(name = "job_id", nullable = false)
private Integer jobID;


@Column(name = "email_address", nullable = false)
private String emailAddress;


@Column(name = "application_status")
private ApplicationStatus applicationStatus;
}
