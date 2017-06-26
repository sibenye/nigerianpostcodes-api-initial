package com.elsynergy.nigerianpostcodes.model.response;

import com.elsynergy.nigerianpostcodes.model.DAO.BaseDAO;

public class LocalGovernmentAreaResponse extends BaseDAO
{
    private String localGovernmentAreaName;

    private String stateCode;

    private String stateName;

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

}
