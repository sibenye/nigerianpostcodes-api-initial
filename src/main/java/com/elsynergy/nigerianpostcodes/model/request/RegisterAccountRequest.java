package com.elsynergy.nigerianpostcodes.model.request;

import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;
import com.elsynergy.nigerianpostcodes.model.enums.RoleEnum;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;

/**
 *
 * @author silver.ibenye
 *
 */
public class RegisterAccountRequest
{
    @ApiParam(value = "Account Name.", required = true)
    @NotNull
    private String accountName;

    @ApiParam(value = "Role", defaultValue="USER", required = true)
    @NotNull
    private RoleEnum role = RoleEnum.USER;

    @ApiParam(value = "Package Name", required = false)
    private PackageEnum packageName;

    @ApiParam(value = "Duration of Subscription in Months.", required = false)
    private Integer durationInMonths;

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

    public RoleEnum getRole()
    {
        return this.role;
    }

    public void setRole(final RoleEnum role)
    {
        this.role = role;
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
