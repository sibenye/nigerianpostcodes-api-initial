package com.elsynergy.nigerianpostcodes.model.DAO;

public class LGA extends BaseDAO
{
    private Integer lgaId;
    private String lga;
    private Integer stateId;
    private String state;
    private String stateCode;

    public Integer getLgaId()
    {
        return this.lgaId;
    }
    public void setLgaId(final Integer lgaId)
    {
        this.lgaId = lgaId;
    }
    public String getLga()
    {
        return this.lga;
    }
    public void setLga(final String lga)
    {
        this.lga = lga;
    }
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
