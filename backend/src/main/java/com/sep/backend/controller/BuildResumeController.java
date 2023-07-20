package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.BuildResumeRequestDto;
import com.sep.backend.dto.BuildResumeResponseDto;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.exception.BuildResumeException;
import com.sep.backend.models.BuildResume;
import com.sep.backend.service.IBuildResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * The type Patient controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class BuildResumeController {

    private final IBuildResumeService iBuildResumeService;

    /**
     * Instantiates a new Patient controller.
     *
     * @param iBuildResumeService the patient service
     */
    @Autowired
    public BuildResumeController(IBuildResumeService iBuildResumeService){
        this.iBuildResumeService = iBuildResumeService;
    }

    /**
     * Register patient response dto.
     *
     * @param buildResumeRequestDto the patient registration request dto
     * @return the response dto
     */
    @RequestMapping(method = RequestMethod.POST, value = UriConstants.BUILD_RESUME)
    public ResponseDto<BuildResumeResponseDto> buildResume(@RequestBody BuildResumeRequestDto buildResumeRequestDto){
        BuildResume buildResume;
        try{
            buildResume = iBuildResumeService.saveResume(buildResumeRequestDto);
        } catch (BuildResumeException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                BuildResumeResponseDto.builder()
                        .name(buildResume.getName())
                        .email(buildResume.getEmail())
                        .phoneNumber(buildResume.getPhoneNumber())
                        .skills(buildResume.getSkills())
                        .linkedIn(buildResume.getLinkedIn())
                        .exp_org1(buildResume.getExp_org1())
                        .exp_pos1(buildResume.getExp_pos1())
                        .exp_dur1(buildResume.getExp_dur1())
                        .exp_dec1(buildResume.getExp_dec1())
                        .edu1_school(buildResume.getEdu1_school())
                        .edu1_degree(buildResume.getEdu1_degree())
                        .edu1_year(buildResume.getEdu1_year())
                        .edu1_dur(buildResume.getEdu1_dur())
                        .proj1_title(buildResume.getProj1_title())
                        .proj1_desc(buildResume.getProj1_desc())
                        .proj1_link(buildResume.getProj1_link())
                        .extra_1(buildResume.getExtra_1())
                        .build()
        );
    }

//    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_DOCTOR_LIST)
//    public ResponseDto<List<BuildResume>> getResume(){
//
//        List<BuildResume> list;
//        try{
//            list = iBuildResume.getResumeDetails();
//        }
//        catch (Exception e){
//            log.error("Error occurred :: " , e);
//            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
//        }
//        return new ResponseDto<>(list);
//    }

//    @RequestMapping(method = RequestMethod.DELETE, value = UriConstants.DELETE_DOCTOR)
//    private ResponseDto<String> deleteDoctor(@RequestParam(name = "email_address")String emailAddress){
//        try{
//            iBuildResume.deleteDoctor(emailAddress);
//            return new ResponseDto<>("Record Deleted Successfully");
//        }
//        catch (Exception e){
//            log.error("Error occurred :: " , e);
//            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
//        }
//    }

}
