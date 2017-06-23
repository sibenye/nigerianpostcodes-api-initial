package com.elsynergy.nigerianpostcodes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.prop")
public class AppProperties
{
    private String environment;

    private String appName;

    private String apiKeyName;

    public String getEnvironment()
    {
        return this.environment;
    }

    public String getAppName()
    {
        return this.appName;
    }

    public void setAppName(final String appName)
    {
        this.appName = appName;
    }

    public String getApiKeyName()
    {
        return this.apiKeyName;
    }

    public void setApiKeyName(final String apiKeyName)
    {
        this.apiKeyName = apiKeyName;
    }

}
