package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;
import com.elsynergy.nigerianpostcodes.model.request.RegisterUserRequest;
import com.elsynergy.nigerianpostcodes.service.userentities.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

/**
 * Account Controller.
 *
 * @author silver.ibenye
 *
 */
@RestController
@RequestMapping("/accounts")
public class AccountController
{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Register new account")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerAccount(@Valid @ModelAttribute final RegisterUserRequest request) {
        return this.userService.registerUser(request);
    }

}
