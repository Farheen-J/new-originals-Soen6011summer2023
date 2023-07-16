package com.sep.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Counselor.
 */
@Entity
@Table(name = "employer")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employer extends BaseModel {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email_address", unique = true)
    private String emailAddress;

    @Column(name = "company_name", nullable = false)
    private String company_name;

    @Column(name = "designation",nullable = false)
    private String designation;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

}
