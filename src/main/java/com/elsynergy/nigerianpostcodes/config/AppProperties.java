package com.elsynergy.nigerianpostcodes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.prop")
public class AppProperties
{
    private String environment;

    public String getEnvironment()
    {
        return this.environment;
    }

}
