package com.elsynergy.nigerianpostcodes.model.Response;

public class FacilityPostcode
{
    private String facility;
    private String lga;
    private String state;
    private Integer postcode;

    public String getFacility()
    {
        return this.facility;
    }
    public void setFacility(final String facility)
    {
        this.facility = facility;
    }
    public String getLga()
    {
        return this.lga;
    }
    public void setLga(final String lga)
    {
        this.lga = lga;
    }
    public String getState()
    {
        return this.state;
    }
    public void setState(final String state)
    {
        this.state = state;
    }
    public Integer getPostcode()
    {
        return this.postcode;
    }
    public void setPostcode(final Integer postcode)
    {
        this.postcode = postcode;
    }

}
