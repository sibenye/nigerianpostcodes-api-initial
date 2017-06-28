package com.elsynergy.nigerianpostcodes.model.request;

import io.swagger.annotations.ApiParam;

/**
 *
 * @author silver.ibenye
 *
 */
public class RuralPostcodeRequest extends PostcodeRequest
{
    @ApiParam(value = "Local Government Area Name.")
    private String localGovtAreaName;

    @ApiParam(value = "District.")
    private String district;

    @ApiParam(value = "Town.")
    private String town;

    public String getLocalGovtAreaName()
    {
        return this.localGovtAreaName;
    }

    public void setLocalGovtAreaName(final String localGovtAreaName)
    {
        this.localGovtAreaName = localGovtAreaName;
    }

    public String getDistrict()
    {
        return this.district;
    }

    public void setDistrict(final String district)
    {
        this.district = district;
    }

    public String getTown()
    {
        return this.town;
    }

    public void setTown(final String town)
    {
        this.town = town;
    }
}
