package com.elsynergy.nigerianpostcodes.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;

/**
 *
 * @author silver.ibenye
 *
 */
public class AccountSubscribeRequest
{
    @ApiParam(value = "Account Name.", required = true)
    @NotNull
    private String accountName;

    @ApiParam(value = "Duration of Subscription in Months.", required = true)
    @NotNull
    private Integer durationInMonths;

    public String getAccountName()
    {
        return this.accountName;
    }

    public void setAccountName(final String accountName)
    {
        this.accountName = accountName;
    }

    public Integer getDurationInMonths()
    {
        return this.durationInMonths;
    }

    public void setDurationInMonths(final Integer durationInMonths)
    {
        this.durationInMonths = durationInMonths;
    }

}
