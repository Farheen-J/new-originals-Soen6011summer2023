package com.sep.backend.controller;

import com.sep.backend.dto.*;
import com.sep.backend.models.Candidate;
import com.sep.backend.constants.UriConstants;
import com.sep.backend.service.ICandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.sep.backend.exception.CandidateRegistrationException;
import org.springframework.web.multipart.MultipartFile;

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

     @RequestMapping(method = RequestMethod.POST, value = UriConstants.UPLOAD_RESUME, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     public ResponseDto<UploadResumeResponse> uploadResume(@RequestParam("email_address") String emailAddress, @RequestParam("uploaded_resume") MultipartFile uploadedResume) {
          Candidate candidate;
          try {
               byte [] resumeData = uploadedResume.getBytes();
              candidate = iCandidateService.uploadResume(emailAddress, resumeData);
          } catch (Exception e) {
               log.error("Error occurred :: ", e);
               return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
          }
          return new ResponseDto<>(
                  UploadResumeResponse.builder()
                          .emailAddress(candidate.getEmailAddress())
                          .uploadedResume(candidate.getUploadedResume())
                          .build()
          );
     }

     @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_UPLOADED_RESUME)
     public ResponseEntity<?> getUploadedResume(@RequestParam(name = "email_address") String emailAddress)
     {
          Candidate candidate = iCandidateService.findByEmailAddress(emailAddress);
          if(candidate == null)
          {
               //custom exception for no such candidate found
          }
          byte [] resumeData = candidate.getUploadedResume();
          return ResponseEntity.status(HttpStatus.OK)
                  .contentType(MediaType.APPLICATION_PDF)
                  .body(resumeData);
     }
}
