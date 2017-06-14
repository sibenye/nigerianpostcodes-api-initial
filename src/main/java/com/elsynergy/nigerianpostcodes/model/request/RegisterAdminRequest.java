package com.elsynergy.nigerianpostcodes.model.request;

import com.elsynergy.nigerianpostcodes.model.enums.RoleEnum;

/**
 *
 * @author silver.ibenye
 *
 */
public class RegisterAdminRequest extends RegisterUserRequest
{
    @Override
    public RoleEnum getRole()
    {
        return RoleEnum.ADMIN;
    }

}
