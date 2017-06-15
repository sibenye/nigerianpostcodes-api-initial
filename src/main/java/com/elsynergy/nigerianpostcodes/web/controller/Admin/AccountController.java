package com.elsynergy.nigerianpostcodes.web.controller.Admin;

import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.service.userentities.UserService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

/**
 * Account Controller.
 *
 * @author silver.ibenye
 *
 */
@RestController
@RequestMapping("/admin/accounts")
public class AccountController
{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Register new Account")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AccountResponse registerAccount(@Valid @ModelAttribute final RegisterAccountRequest request) {
        return this.userService.registerUser(request);
    }

    @ApiOperation(value = "Get User Account")
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public AccountResponse getAccount(@PathVariable final String username) throws ResourceNotFoundException {
        return this.userService.getUserAccount(username);
    }

}
