package com.elsynergy.nigerianpostcodes.model.request;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiParam;

/**
 *
 * @author silver.ibenye
 *
 */
public class AccountIpAccessRequest
{
    @ApiParam(value = "Account Name.", required = true)
    @NotEmpty
    private String accountName;

    @ApiParam(value = "Comma seperated ipAddresses.", required = true)
    @NotEmpty
    private String allowedIpAddresses;

    public String getAccountName()
    {
        return this.accountName;
    }

    public void setAccountName(final String accountName)
    {
        this.accountName = accountName;
    }

    public String getAllowedIpAddresses()
    {
        return this.allowedIpAddresses;
    }

    public void setAllowedIpAddresses(final String allowedIpAddresses)
    {
        this.allowedIpAddresses = allowedIpAddresses;
    }

}
