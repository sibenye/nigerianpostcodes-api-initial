package com.elsynergy.nigerianpostcodes.model.response;

import com.elsynergy.nigerianpostcodes.model.DAO.BaseDAO;

public class RuralPostcodeResponse extends BaseDAO
{
    private String town;

    private String district;

    private String localGovernmentAreaName;

    private String stateCode;

    private String stateName;

    private String postcode;

    public String getTown()
    {
        return town;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
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
