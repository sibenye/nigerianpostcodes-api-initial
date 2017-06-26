package com.elsynergy.nigerianpostcodes.model.response;

import com.elsynergy.nigerianpostcodes.model.DAO.BaseDAO;

public class StateResponse extends BaseDAO
{
    private String stateCode;

    private String stateName;

    public String getStateCode()
    {
        return this.stateCode;
    }

    public void setStateCode(final String stateCode)
    {
        this.stateCode = stateCode;
    }

    public String getStateName()
    {
        return this.stateName;
    }

    public void setStateName(final String stateName)
    {
        this.stateName = stateName;
    }

}
