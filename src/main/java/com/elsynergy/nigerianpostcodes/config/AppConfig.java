package com.elsynergy.nigerianpostcodes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig
{
    @Autowired
    private AppProperties properties;

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate( final DataSource dataSource )
    {
        return new JdbcTemplate( dataSource );
    }

    @Bean
    @Primary
    @ConfigurationProperties ( prefix = "app.datasource" )
    public DataSource dataSource()
        throws ClassNotFoundException
    {
        return DataSourceBuilder.create().build();
    }

}
