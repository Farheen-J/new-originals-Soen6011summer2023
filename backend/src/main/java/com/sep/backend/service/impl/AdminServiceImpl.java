package com.sep.backend.service.impl;

import com.sep.backend.dto.LoginResponse;

import com.sep.backend.exception.LoginException;

import com.sep.backend.models.Admin;

import com.sep.backend.repository.*;
import com.sep.backend.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Admin service.
 */
@Service
public class AdminServiceImpl extends  UserLogin implements IAdminService {

    private AdminRepository adminRepository;

    /**
     * Instantiates a new Admin service.
     *
     * @param adminRepository the admin repository
     */
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;

    }


    public  LoginResponse getLoginDetails(String email, String password) throws LoginException {
        LoginResponse loginResponse=new LoginResponse();
        Admin admin = adminRepository.findFirstByEmailAddressAndPassword(email, password);
        if(admin ==null){
            loginResponse.setLogged(false);
            return loginResponse;
        }
        loginResponse.setLogged(true);
        loginResponse.setAge(admin.getAge());
        loginResponse.setGender(admin.getGender());
        loginResponse.setFirstName(admin.getFirstName());
        loginResponse.setMiddleName(admin.getMiddleName());
        loginResponse.setLastName(admin.getLastName());
        loginResponse.setPhoneNumber(admin.getPhoneNumber());
        loginResponse.setEmailAddress(admin.getEmailAddress());
        return loginResponse;
    }



}
