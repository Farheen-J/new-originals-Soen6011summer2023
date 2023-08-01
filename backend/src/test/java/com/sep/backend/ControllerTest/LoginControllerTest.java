package com.sep.backend.ControllerTest;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.controller.LoginController;
import com.sep.backend.dto.LoginResponse;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.exception.LoginException;
import com.sep.backend.service.ILoginService;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private ILoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
   public void testGetLoginResponse_ValidCredentials_Success() throws LoginException {
        // Arrange
        String email = "user@example.com";
        String password = "password123";
        String userType = "user";

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setLogged(true);

        when(loginService.getLoginDetails(email, password, userType))
                .thenReturn(expectedResponse);


        ResponseDto<LoginResponse> responseDto = loginController.getLoginResponse(email, password, userType);


        assertEquals(expectedResponse, responseDto.getData());
    }

    @Test
    public void testGetLoginResponse_InvalidCredentials_Failure() throws LoginException {

        String email = "user@example.com";
        String password = "invalidPassword";
        String userType = "user";


        when(loginService.getLoginDetails(anyString(), anyString(), anyString()))
                .thenThrow(new LoginException("Invalid credentials"));

        ResponseDto<LoginResponse> responseDto = loginController.getLoginResponse(email, password, userType);


        assertEquals(false, responseDto.getData().isLogged());
    }

}