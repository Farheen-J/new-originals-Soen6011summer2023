package com.sep.backend.service.impl;


import com.sep.backend.dto.LoginResponse;
import com.sep.backend.enums.UserType;
import com.sep.backend.exception.LoginException;
import com.sep.backend.service.ICandidateService;
import com.sep.backend.service.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sep.backend.service.ILoginService;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements ILoginService {

    private ICandidateService iCandidateService;
    private IEmployerService iEmployerService;

    private final HashMap<UserType, UserLogin> hsmap = new HashMap<>();

    @Autowired
    public LoginServiceImpl(ICandidateService iCandidateService, IEmployerService iEmployerService) {
        this.iCandidateService=iCandidateService;
        this.iEmployerService=iEmployerService;

        this.hsmap.put(UserType.CANDIDATE, (UserLogin) iCandidateService);
        this.hsmap.put(UserType.EMPLOYER, (UserLogin) iEmployerService);

    }

    @Override
    public LoginResponse getLoginDetails(String email, String password, String userType) throws LoginException {
        return hsmap.get(UserType.getUserType(userType)).getLoginDetails(email, password);
    }
}
