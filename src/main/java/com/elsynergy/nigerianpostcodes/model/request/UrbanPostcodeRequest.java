package com.elsynergy.nigerianpostcodes.model.request;

import io.swagger.annotations.ApiParam;

/**
 *
 * @author silver.ibenye
 *
 */
public class UrbanPostcodeRequest extends PostcodeRequest
{
    @ApiParam(value = "Town.")
    private String town;

    @ApiParam(value = "Area.")
    private String area;

    @ApiParam(value = "Street.")
    private String street;

    public String getTown()
    {
        return this.town;
    }

    public void setTown(final String town)
    {
        this.town = town;
    }

    public String getArea()
    {
        return this.area;
    }

    public void setArea(final String area)
    {
        this.area = area;
    }

    public String getStreet()
    {
        return this.street;
    }

    public void setStreet(final String street)
    {
        this.street = street;
    }
}
