package com.elsynergy.nigerianpostcodes.model.request;

import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiParam;

/**
 *
 * @author silver.ibenye
 *
 */
public class AccountSubscribeRequest
{
    @ApiParam(value = "Account Name.", required = true)
    @NotEmpty
    private String accountName;

    @ApiParam(value = "Package Name", required = true)
    @NotEmpty
    private PackageEnum packageName;

    @ApiParam(value = "Duration of Subscription in Months.", defaultValue="1", required = false)
    private Integer durationInMonths = 1;

    @ApiParam(value = "Renew Subscription?", defaultValue="false", required = false)
    private boolean renewSubscription = false;

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
