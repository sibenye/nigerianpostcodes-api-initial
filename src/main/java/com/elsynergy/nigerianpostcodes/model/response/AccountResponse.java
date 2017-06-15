package com.elsynergy.nigerianpostcodes.model.response;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public class AccountResponse
{
    private String username;
    private Boolean active;
    private String packageType;
    private String role;
    private List<String> features;

    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(final String username)
    {
        this.username = username;
    }
    public Boolean getActive()
    {
        return this.active;
    }
    public void setActive(final Boolean active)
    {
        this.active = active;
    }
    public String getPackageType()
    {
        return this.packageType;
    }
    public void setPackageType(final String packageType)
    {
        this.packageType = packageType;
    }
    public List<String> getFeatures()
    {
        return this.features;
    }
    public void setFeatures(final List<String> features)
    {
        this.features = features;
    }
    public String getRole()
    {
        return this.role;
    }
    public void setRole(final String role)
    {
        this.role = role;
    }

}
