package com.sep.backend.service.impl;

import com.sep.backend.dto.LoginResponse;
import com.sep.backend.enums.Gender;
import com.sep.backend.exception.CandidateRegistrationException;
import com.sep.backend.dto.CandidateRegistrationRequestDto;
import com.sep.backend.exception.LoginException;
import com.sep.backend.models.Candidate;
import com.sep.backend.repository.CandidateRepository;
import com.sep.backend.service.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * The type Candidate service.
 */
@Service
public class CandidateServiceImpl extends UserLogin implements ICandidateService {

    private CandidateRepository candidateRepository;

    /**
     * Instantiates a new Patient service.
     *
     * @param candidateRepository the patient repository
     */
    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate saveCandidate(CandidateRegistrationRequestDto candidateRegistrationRequestDto) throws CandidateRegistrationException {

        if (Objects.isNull(candidateRegistrationRequestDto.getEmailAddress())) throw new CandidateRegistrationException("Email Address is required");
        else if(Objects.isNull(candidateRegistrationRequestDto.getPassword())) throw new CandidateRegistrationException("Password is required");

        if(Objects.nonNull(candidateRepository.findFirstByEmailAddress(candidateRegistrationRequestDto.getEmailAddress()))){
            throw new CandidateRegistrationException("Candidate with same email address already exists");
        }

        Candidate candidate = Candidate.builder()
                .firstName(candidateRegistrationRequestDto.getFirstName())
                .middleName(candidateRegistrationRequestDto.getMiddleName())
                .lastName(candidateRegistrationRequestDto.getLastName())
                .age(candidateRegistrationRequestDto.getAge())
                .emailAddress(candidateRegistrationRequestDto.getEmailAddress())
                .gender(Gender.getGender(candidateRegistrationRequestDto.getGender()))
                .phoneNumber(candidateRegistrationRequestDto.getPhoneNumber())
                .password(candidateRegistrationRequestDto.getPassword())
                .build();
        candidateRepository.save(candidate);
        return candidate;
    }

    public LoginResponse getLoginDetails(String email, String password) throws LoginException {
        LoginResponse loginResponse=new LoginResponse();
        Candidate candidate=candidateRepository.findFirstByEmailAddressAndPassword(email, password);
        if(candidate==null){
            loginResponse.setLogged(false);
            return loginResponse;
        }
        loginResponse.setId(candidate.getId());
        loginResponse.setLogged(true);
        loginResponse.setAge(candidate.getAge());
        loginResponse.setGender(candidate.getGender());
        loginResponse.setFirstName(candidate.getFirstName());
        loginResponse.setMiddleName(candidate.getMiddleName());
        loginResponse.setLastName(candidate.getLastName());
        loginResponse.setPhoneNumber(candidate.getPhoneNumber());
        loginResponse.setEmailAddress(candidate.getEmailAddress());
        return loginResponse;
    }
    @Override
    public Candidate findByEmailId(String emailId) {
        return candidateRepository.findFirstByEmailAddress(emailId);
    }

    @Override
    public List<Candidate> getPatientsList() {
        return candidateRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePatient(String emailAddress) {
        Candidate candidate = findByEmailId(emailAddress);
        candidateRepository.deleteAllByEmailAddress(emailAddress);
    }


}
