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

    @ApiParam(value = "Package Name")
    @NotNull
    private PackageEnum packageName = PackageEnum.BASIC;

    @ApiParam(value = "Role")
    @NotNull
    private RoleEnum role = RoleEnum.USER;

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

}
