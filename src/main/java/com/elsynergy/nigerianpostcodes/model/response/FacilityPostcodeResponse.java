package com.elsynergy.nigerianpostcodes.model.response;

import com.elsynergy.nigerianpostcodes.model.DAO.BaseDAO;

public class FacilityPostcodeResponse extends BaseDAO
{
    private String facilityName;

    private String localGovernmentAreaName;

    private String stateCode;

    private String stateName;

    private String postcode;

    public String getFacilityName()
    {
        return facilityName;
    }

    public void setFacilityName(String facilityName)
    {
        this.facilityName = facilityName;
    }

    public String getLocalGovernmentAreaName()
    {
        return localGovernmentAreaName;
    }

    public void setLocalGovernmentAreaName(String localGovernmentAreaName)
    {
        this.localGovernmentAreaName = localGovernmentAreaName;
    }

    public String getStateCode()
    {
        return stateCode;
    }

    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

}
