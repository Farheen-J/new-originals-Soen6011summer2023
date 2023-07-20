package com.sep.backend.service.impl;

import com.sep.backend.dto.BuildResumeRequestDto;

import com.sep.backend.exception.BuildResumeException;

import com.sep.backend.models.BuildResume;


import com.sep.backend.repository.BuildResumeRepository;

import com.sep.backend.service.IBuildResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * The type Counselor service.
 */
@Service
public class BuildResumeServiceImpl implements IBuildResumeService {

    private BuildResumeRepository buildResumeRepository;


    /**
     * Instantiates a new Patient service.
     *
     * @param buildResumeRepository the counselor repository
     */
    @Autowired
    public BuildResumeServiceImpl(BuildResumeRepository buildResumeRepository){
        this.buildResumeRepository = buildResumeRepository;

    }

    @Override
    public BuildResume saveResume(BuildResumeRequestDto buildResumeRequestDto) throws BuildResumeException {

        if (Objects.isNull(buildResumeRequestDto.getEmail())) throw new BuildResumeException("Email Address is required");

        if(Objects.nonNull(buildResumeRepository.findFirstByEmailAddress(buildResumeRequestDto.getEmail()))){
            throw new BuildResumeException("Resume with same email address already exists");
        }

        BuildResume buildResume= new BuildResume();
        buildResume = BuildResume.builder()
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
                        .build();

        buildResumeRepository.save(buildResume);
        return buildResume;
    }
//    public  LoginResponse getLoginDetails(String email, String password) throws LoginException{
//        LoginResponse loginResponse=new LoginResponse();
//        Doctor doctor= buildResumeRepository.findFirstByEmailAddressAndPassword(email, password);
//        if(doctor==null){
//            loginResponse.setLogged(false);
//            return loginResponse;
//        }
//        loginResponse.setId(doctor.getId());
//        loginResponse.setLogged(true);
//        loginResponse.setAge(doctor.getAge());
//        loginResponse.setGender(doctor.getGender());
//        loginResponse.setFirstName(doctor.getFirstName());
//        loginResponse.setMiddleName(doctor.getMiddleName());
//        loginResponse.setLastName(doctor.getLastName());
//        loginResponse.setPhoneNumber(doctor.getPhoneNumber());
//        loginResponse.setEmailAddress(doctor.getEmailAddress());
//        return loginResponse;
//    }


    public List<BuildResume> getResume() {
        return buildResumeRepository.findAll();
    }

//    @Override
//    @Transactional
//    public void deleteDoctor(String emailAddress) {
//        Doctor doctor = buildResumeRepository.findFirstByEmailAddress(emailAddress);
//        buildResumeRepository.deleteAllByEmailAddress(emailAddress);
//        List<Appointment> appointmentList = appointmentRepository.findAllByDoctorRegistrationNumber(doctor.getId().toString());
//        appointmentList.forEach(appointment -> {appointment.setDoctorRegistrationNumber(null);appointment.setStatus(0);appointment.setAppointmentStartTime(null);appointment.setCounsellorRegistrationNumber(null);});
//        appointmentRepository.saveAll(appointmentList);
//    }
//
//    @Override
//    public Doctor findById(Integer id) {
//        return buildResumeRepository.findFirstById(id);
//    }


}
