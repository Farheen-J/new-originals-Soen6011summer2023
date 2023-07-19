package com.sep.backend.service;

import com.sep.backend.dto.LoginResponse;

import com.sep.backend.exception.LoginException;



/**
 * The interface Admin service.
 */
public interface IAdminService {



    LoginResponse getLoginDetails(String email, String password) throws LoginException;

}
