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
    private String packageName;
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
    public String getPackageName()
    {
        return this.packageName;
    }
    public void setPackageName(final String packageName)
    {
        this.packageName = packageName;
    }
    public List<String> getFeatures()
    {
        return this.features;
    }
    public void setFeatures(final List<String> features)
    {
        this.features = features;
    }

}
