package com.elsynergy.nigerianpostcodes.model.request;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiParam;

/**
 *
 * @author silver.ibenye
 *
 */
public class PostcodeRequest
{
    @ApiParam(value = "State Code.", required = true)
    @NotEmpty
    private String stateCode;

    public String getStateCode()
    {
        return this.stateCode;
    }

    public void setStateCode(final String stateCode)
    {
        this.stateCode = stateCode;
    }

}
