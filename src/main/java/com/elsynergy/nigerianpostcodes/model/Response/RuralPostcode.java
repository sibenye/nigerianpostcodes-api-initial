package com.elsynergy.nigerianpostcodes.model.Response;

public class RuralPostcode
{
    private String town;
    private String lga;
    private String state;
    private String district;
    private Integer postcode;

    public String getTown()
    {
        return this.town;
    }
    public void setTown(final String town)
    {
        this.town = town;
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
    public String getDistrict()
    {
        return this.district;
    }
    public void setDistrict(final String district)
    {
        this.district = district;
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
