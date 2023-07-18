package com.sep.backend.controller;

import com.sep.backend.models.Candidate;
import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.CandidateRegistrationRequestDto;
import com.sep.backend.dto.CandidateRegistrationResponseDto;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.service.ICandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sep.backend.exception.CandidateRegistrationException;
import java.util.Collections;

/**
 * The type Candidate controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class CandidateController {

private final ICandidateService iCandidateService;

/**
 * Instantiates a new Candidate controller.
 *
 * @param iCandidateService the candidate service
 */
@Autowired
public CandidateController(ICandidateService iCandidateService){
     this.iCandidateService = iCandidateService;
}
/**
 * Register candidate response dto.
 *
 * @param candidateRegistrationRequestDto the candidate registration request dto
 * @return the response dto
 */
@RequestMapping(method = RequestMethod.POST, value = UriConstants.REGISTER_CANDIDATE)
public ResponseDto<CandidateRegistrationResponseDto> registerCandidate(@RequestBody CandidateRegistrationRequestDto candidateRegistrationRequestDto){
     Candidate candidate;
     try{
          candidate = iCandidateService.saveCandidate(candidateRegistrationRequestDto);
     } catch (CandidateRegistrationException e) {
          return new ResponseDto<>(Collections.singletonList(e.getMessage()));
     }
     catch (Exception e){
          log.error("Error occurred :: " , e);
          return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
     }
     return new ResponseDto<>(
             CandidateRegistrationResponseDto.builder()
                     .id(candidate.getId())
                     .firstName(candidate.getFirstName())
                     .middleName(candidate.getMiddleName())
                     .lastName(candidate.getLastName())
                     .age(candidate.getAge())
                     .emailAddress(candidate.getEmailAddress())
                     .phoneNumber(candidate.getPhoneNumber())
                     .gender(candidate.getGender().getGenderDisplay())
                     .build()
     );
     }
}
