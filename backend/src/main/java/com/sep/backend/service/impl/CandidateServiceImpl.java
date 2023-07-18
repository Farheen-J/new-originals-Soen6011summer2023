package com.sep.backend.service.impl;

import com.sep.backend.enums.Gender;
import com.sep.backend.exception.CandidateRegistrationException;
import com.sep.backend.dto.CandidateRegistrationRequestDto;
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
public class CandidateServiceImpl implements ICandidateService {

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
