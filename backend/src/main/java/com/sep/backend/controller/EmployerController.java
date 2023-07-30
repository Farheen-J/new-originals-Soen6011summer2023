package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.EmployerRegistrationRequestDto;
import com.sep.backend.dto.EmployerRegistrationResponseDto;
import com.sep.backend.dto.JobListingResponseDto;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.exception.EmployerRegistrationException;
import com.sep.backend.models.Employer;
import com.sep.backend.models.JobListing;
import com.sep.backend.service.IEmployerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * The type Employer controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class EmployerController {
    private final IEmployerService iEmployerService;

    /**
     * Instantiates a new  controller.
     *
     * @param iEmployerService the  service
     */

    @Autowired
    public EmployerController(IEmployerService iEmployerService){
        this.iEmployerService = iEmployerService;
    }
    /**
     * Register employer response dto.
     *
     * @param employerRegistrationRequestDto the employer registration request dto
     * @return the response dto
     */
    @RequestMapping(method = RequestMethod.POST, value = UriConstants.REGISTER_EMPLOYER)
    public ResponseDto<EmployerRegistrationResponseDto> registerEmployer(@RequestBody EmployerRegistrationRequestDto employerRegistrationRequestDto){
        Employer employer;
        try{
            employer = iEmployerService.saveEmployer(employerRegistrationRequestDto);
        } catch (EmployerRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred ::", e );
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                EmployerRegistrationResponseDto.builder()
                        .id(employer.getId())
                        .firstName(employer.getFirstName())

                        .lastName(employer.getLastName())

                        .emailAddress(employer.getEmailAddress())
                        .phoneNumber(employer.getPhoneNumber())

                        .registrationNumber(employer.getRegistrationNumber())
                        .designation(employer.getDesignation())
                        .companyName(employer.getCompany_name())
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_EMPLOYER_LIST)
    public ResponseDto<List<Employer>> getEmployer(){

        List<Employer> list;
        try{
            list = iEmployerService.getEmployerList();
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(list);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = UriConstants.DELETE_EMPLOYER)
    private ResponseDto<String> deleteEmployer(@RequestParam(name = "email_address")String emailAddress){
        try{
            iEmployerService.deleteEmployer(emailAddress);
            return new ResponseDto<>("Record Deleted Successfully");
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

    /**
     * Get employer by ID.
     *
     * @param emailAddress the ID of the employer to retrieve
     * @return the response DTO containing the employer data
     */
    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_EMPLOYER)
    public ResponseDto<EmployerRegistrationResponseDto> getEmployerByEmailAddress(@RequestParam (name = "email_address") String emailAddress) {
        Employer employer;
        try {
            employer = iEmployerService.getEmployerByEmailAddress(emailAddress);

            // Check if the employer exists in the database
            if (employer == null) {
                return new ResponseDto<>(Collections.singletonList("Employer not found"));
            }

            // Create the response DTO containing the job listing data
            EmployerRegistrationResponseDto responseDto = EmployerRegistrationResponseDto.builder()
                    .id(employer.getId())
                    .firstName(employer.getFirstName())
                    .lastName(employer.getLastName())
                    .emailAddress(employer.getEmailAddress())
                    .phoneNumber(employer.getPhoneNumber())
                    .registrationNumber(employer.getRegistrationNumber())
                    .designation(employer.getDesignation())
                    .companyName(employer.getCompany_name())
                    .build();

            return new ResponseDto<>(responseDto);
        } catch (Exception e) {
            log.error("Error occurred while fetching employer: ", e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

}