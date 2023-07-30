package com.sep.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * The type Job Listing.
 */
@Entity
@Table(name = "job_listing")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobListing extends BaseModel{

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

@Column(name = "emoloyer_email", nullable = false)
private String employerEmail;

@Column(name = "description")
private String description;

@Column(name = "requirements")
private String requirements;
}