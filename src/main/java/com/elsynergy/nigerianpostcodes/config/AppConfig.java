package com.elsynergy.nigerianpostcodes.config;

import com.elsynergy.nigerianpostcodes.service.IDateTimeService;
import com.elsynergy.nigerianpostcodes.service.provider.AuditingDateTimeProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableJpaRepositories(basePackages = {
        "com.elsynergy.nigerianpostcodes.repo"
})
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

    @Bean
    DateTimeProvider dateTimeProvider(final IDateTimeService dateTimeService) {
        return new AuditingDateTimeProvider(dateTimeService);
    }

}
