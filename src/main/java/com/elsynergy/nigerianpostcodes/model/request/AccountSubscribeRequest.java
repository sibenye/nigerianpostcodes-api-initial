package com.elsynergy.nigerianpostcodes.model.request;

import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;

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

    @ApiParam(value = "Package Name", required = true)
    @NotNull
    private PackageEnum packageName;

    @ApiParam(value = "Duration of Subscription in Months.", required = true)
    @NotNull
    private Integer durationInMonths;

    @ApiParam(value = "Renew Subscription?", required = false)
    private boolean renewSubscription;

    public String getAccountName()
    {
        return this.accountName;
    }

    public void setAccountName(final String accountName)
    {
        this.accountName = accountName;
    }

    public PackageEnum getPackageName()
    {
        return this.packageName;
    }

    public void setPackageName(final PackageEnum packageName)
    {
        this.packageName = packageName;
    }

    public Integer getDurationInMonths()
    {
        return this.durationInMonths;
    }

    public void setDurationInMonths(final Integer durationInMonths)
    {
        this.durationInMonths = durationInMonths;
    }

    public boolean isRenewSubscription()
    {
        return this.renewSubscription;
    }

    public void setRenewSubscription(final boolean renewSubscription)
    {
        this.renewSubscription = renewSubscription;
    }

}
