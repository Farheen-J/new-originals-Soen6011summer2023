package com.sep.backend.repository;

import com.sep.backend.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface employer repository.
 */
public interface EmployerRepository extends JpaRepository<Employer, Integer> {


    /**
     * Find first by email address Employer.
     *
     * @param emailAddress the email address
     * @return the patient
     */
    Employer findFirstByEmailAddress(String emailAddress);

    List<Employer> findAll();

    void deleteAllByEmailAddress(String emailAddress);

    Employer findFirstById(Integer id);


}
