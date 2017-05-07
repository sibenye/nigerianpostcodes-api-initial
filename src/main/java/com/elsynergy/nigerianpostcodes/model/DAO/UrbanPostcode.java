package com.elsynergy.nigerianpostcodes.model.DAO;

/**
 *
 * @author silver.ibenye
 *
 */
public class UrbanPostcode extends BaseDAO
{
    private String street;
    private String area;
    private String town;
    private String state;
    private Integer postcode;

    public String getStreet()
    {
        return this.street;
    }
    public void setStreet(final String street)
    {
        this.street = street;
    }
    public String getArea()
    {
        return this.area;
    }
    public void setArea(final String area)
    {
        this.area = area;
    }
    public String getTown()
    {
        return this.town;
    }
    public void setTown(final String town)
    {
        this.town = town;
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
