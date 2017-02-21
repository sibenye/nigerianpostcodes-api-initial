package com.elsynergy.nigerianpostcodes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    public void configure(final AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.authenticationProvider(new AuthenticationProvider()
        {

            @Override
            public boolean supports(final Class<?> authentication)
            {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public Authentication authenticate(final Authentication authentication) throws AuthenticationException
            {
                // TODO Auto-generated method stub
                return null;
            }
        });
    }

    @Override
    protected void configure(final HttpSecurity http)
            throws Exception
    {
        // Set route permissions.
        http.authorizeRequests()
            // Permit all.
            .antMatchers(HttpMethod.GET, "/health").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers(HttpMethod.GET, "/**").permitAll()
            // Defaults
            .anyRequest().anonymous()
            .and().csrf().disable();

    }

}
