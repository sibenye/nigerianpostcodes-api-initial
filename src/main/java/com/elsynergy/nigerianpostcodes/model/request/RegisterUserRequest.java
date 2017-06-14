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
public class RegisterUserRequest
{
    @ApiParam(value = "UserName.", required = true)
    @NotNull
    private String username;

    @ApiParam(value = "Password.", required = true)
    @NotNull
    private String password;

    @ApiParam(value = "Package Name")
    @NotNull
    private PackageEnum packageName = PackageEnum.BASIC;

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
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
        return RoleEnum.USER;
    }

}
