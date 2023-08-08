package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;

import com.sep.backend.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Admin controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class AdminController {

    private final IAdminService iAdminService;

    /**
     * Instantiates a new  controller.
     *
     * @param iAdminService the  service
     */
    @Autowired
    public AdminController(IAdminService iAdminService){
        this.iAdminService = iAdminService;
    }



}
