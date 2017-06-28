package com.elsynergy.nigerianpostcodes.model.request;

import io.swagger.annotations.ApiParam;

public class FacilityPostcodeRequest extends PostcodeRequest
{
    @ApiParam(value = "Local Government Area Name.")
    private String localGovtAreaName;

    @ApiParam(value = "Post Office Facility Name.")
    private String facilityName;

    public String getLocalGovtAreaName()
    {
        return this.localGovtAreaName;
    }

    public void setLocalGovtAreaName(final String localGovtAreaName)
    {
        this.localGovtAreaName = localGovtAreaName;
    }

    public String getFacilityName()
    {
        return this.facilityName;
    }

    public void setFacilityName(final String facilityName)
    {
        this.facilityName = facilityName;
    }

}
