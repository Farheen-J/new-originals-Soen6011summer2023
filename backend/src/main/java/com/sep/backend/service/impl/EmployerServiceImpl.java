package com.sep.backend.service.impl;
import com.sep.backend.dto.EmployerRegistrationRequestDto;

import com.sep.backend.dto.LoginResponse;
import com.sep.backend.exception.EmployerRegistrationException;

import com.sep.backend.exception.LoginException;
import com.sep.backend.models.Employer;

import com.sep.backend.repository.EmployerRepository;

import com.sep.backend.service.IEmployerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * The type employer service.
 */
@Service
public class EmployerServiceImpl extends UserLogin implements IEmployerService {

    private EmployerRepository employerRepository;

    /**
     * Instantiates a new Candidate service.
     *
     * @param employerRepository the employer repository
     */
    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository){
        this.employerRepository = employerRepository;
    }

    @Override
    public Employer saveEmployer(EmployerRegistrationRequestDto employerRegistrationRequestDto) throws EmployerRegistrationException {

        if (Objects.isNull(employerRegistrationRequestDto.getEmailAddress())) throw new EmployerRegistrationException("Email Address is required");
        else if(Objects.isNull(employerRegistrationRequestDto.getPassword())) throw new EmployerRegistrationException("Password is required");

        if(Objects.nonNull(employerRepository.findFirstByEmailAddress(employerRegistrationRequestDto.getEmailAddress()))){
            throw new EmployerRegistrationException("Employer with same email address already exists");
        }

        Employer employer = Employer.builder()
                .firstName(employerRegistrationRequestDto.getFirstName())
                .lastName(employerRegistrationRequestDto.getLastName())
                .emailAddress(employerRegistrationRequestDto.getEmailAddress())
                .designation(employerRegistrationRequestDto.getDesignation())
                .company_name(employerRegistrationRequestDto.getCompanyName())
//                .gender(employerRegistrationRequestDto.getGender())
//                .companyName(employerRegistrationRequestDto.getCompanyName())
                .phoneNumber(employerRegistrationRequestDto.getPhoneNumber())
                .password(employerRegistrationRequestDto.getPassword())
                .registrationNumber(employerRegistrationRequestDto.getRegistrationNumber())
                .build();
        employerRepository.save(employer);
        return employer;
    }
    public LoginResponse getLoginDetails(String email, String password) throws LoginException {
        LoginResponse loginResponse=new LoginResponse();
        Employer employer=employerRepository.findFirstByEmailAddressAndPassword(email, password);
        if(employer==null){
            loginResponse.setLogged(false);
            return loginResponse;
        }
        loginResponse.setId(employer.getId());
        loginResponse.setLogged(true);
        loginResponse.setDesignation(employer.getDesignation());
        loginResponse.setCompanyName(employer.getCompany_name());
        loginResponse.setFirstName(employer.getFirstName());
//        loginResponse.setMiddleName(employer.getMiddleName());
        loginResponse.setLastName(employer.getLastName());
        loginResponse.setPhoneNumber(employer.getPhoneNumber());
        loginResponse.setEmailAddress(employer.getEmailAddress());
        return loginResponse;
    }
//    @Override
//    public EmployerHomepageResponseDto getHomePage(String counsellorId) throws EmployerHomepageException {
//        return null;
//    }


    @Override
    public List<Employer> getEmployerList() {
        return employerRepository.findAll();
    }

    @Override
    public void deleteEmployer(String emailAddress) {

    }


    @Override
    public Employer findById(Integer id) {
        return employerRepository.findFirstById(id);
    }


}
