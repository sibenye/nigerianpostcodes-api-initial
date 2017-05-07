package com.elsynergy.nigerianpostcodes.model.DAO;

/**
 *
 * @author silver.ibenye
 *
 */
public class State extends BaseDAO
{
    private Integer stateId;
    private String state;
    private String stateCode;

    public Integer getStateId()
    {
        return this.stateId;
    }
    public void setStateId(final Integer stateId)
    {
        this.stateId = stateId;
    }
    public String getState()
    {
        return this.state;
    }
    public void setState(final String state)
    {
        this.state = state;
    }
    public String getStateCode()
    {
        return this.stateCode;
    }
    public void setStateCode(final String stateCode)
    {
        this.stateCode = stateCode;
    }

}
