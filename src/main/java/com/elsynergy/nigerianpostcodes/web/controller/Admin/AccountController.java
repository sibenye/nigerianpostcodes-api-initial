package com.elsynergy.nigerianpostcodes.web.controller.Admin;

import com.elsynergy.nigerianpostcodes.model.request.AccountSubscribeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.service.accountentities.AccountService;
import com.elsynergy.nigerianpostcodes.web.exception.BadRequestException;
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
    private AccountService accountService;

    @ApiOperation(value = "Register new Account")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AccountResponse registerAccount(@Valid @ModelAttribute final RegisterAccountRequest request) throws ResourceNotFoundException {
        return this.accountService.registerAccount(request);
    }

    @ApiOperation(value = "Subscribe Account")
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public AccountResponse registerAccount(@Valid @ModelAttribute final AccountSubscribeRequest request) throws ResourceNotFoundException, BadRequestException {
        return this.accountService.subscribeAccount(request);
    }

    @ApiOperation(value = "Get User Account")
    @RequestMapping(value = "/{acctName}", method = RequestMethod.GET)
    public AccountResponse getAccount(@PathVariable final String acctName) throws ResourceNotFoundException {
        return this.accountService.getAccountDetails(acctName);
    }

}
