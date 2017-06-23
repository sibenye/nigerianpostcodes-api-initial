package com.elsynergy.nigerianpostcodes.web.controller.Admin;

import com.elsynergy.nigerianpostcodes.model.request.AccountIpAccessRequest;
import com.elsynergy.nigerianpostcodes.model.request.AccountSubscribeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;
import com.elsynergy.nigerianpostcodes.model.response.ApiResponse;
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

    @ApiOperation(value = "Get User Account")
    @RequestMapping(value = "/{acctName}", method = RequestMethod.GET)
    public ApiResponse getAccount(@PathVariable final String acctName) throws ResourceNotFoundException {
        return new ApiResponse(this.accountService.getAccountDetails(acctName));
    }

    @ApiOperation(value = "Register new Account")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResponse registerAccount(@Valid @RequestBody final RegisterAccountRequest request) throws ResourceNotFoundException {
        return new ApiResponse(this.accountService.registerAccount(request));
    }

    @ApiOperation(value = "Subscribe Account")
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ApiResponse registerAccount(@Valid @RequestBody final AccountSubscribeRequest request) throws ResourceNotFoundException, BadRequestException {
        return new ApiResponse(this.accountService.subscribeAccount(request));
    }

    @ApiOperation(value = "Link ipAddresses for access to Account")
    @RequestMapping(value = "/ipAccess", method = RequestMethod.POST)
    public ApiResponse linkAccountIpAccess(@Valid @RequestBody final AccountIpAccessRequest request) throws ResourceNotFoundException {
        this.accountService.linkIpAccess(request);

        return new ApiResponse(null);
    }

    @ApiOperation(value = "Unlink ipAddresses for access to Account")
    @RequestMapping(value = "/ipAccess", method = RequestMethod.DELETE)
    public ApiResponse unlinkAccountIpAccess(@Valid @ModelAttribute final AccountIpAccessRequest request) throws ResourceNotFoundException {
        this.accountService.unlinkIpAccess(request);

        return new ApiResponse(null);
    }

}
