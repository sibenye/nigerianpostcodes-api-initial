package com.elsynergy.nigerianpostcodes.config;

import com.elsynergy.nigerianpostcodes.auth.ApiKeyAuthenticationFilter;
import com.elsynergy.nigerianpostcodes.auth.CurrentUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CurrentUserDetailsService userDetailsService;

    @Override
    public void configure(final AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.userDetailsService(this.userDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http)
            throws Exception
    {
        // Set route permissions.
        http.addFilter(new ApiKeyAuthenticationFilter(this.userDetailsService))
        .authorizeRequests()
            // Permit all.
            .antMatchers(HttpMethod.GET, "/health").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
            .antMatchers(HttpMethod.GET, "/configuration/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-resources").permitAll()
            .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()

            //.antMatchers(HttpMethod.GET, "/admin/**").hasAuthority(RoleEnum.ADMIN.toString())
            //.antMatchers(HttpMethod.POST, "/admin/**").hasAuthority(RoleEnum.ADMIN.toString())
            //.antMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority(RoleEnum.ADMIN.toString())

            //.antMatchers(HttpMethod.GET, "/**").hasAnyAuthority(RoleEnum.USER.toString(), RoleEnum.ADMIN.toString())
            //.antMatchers(HttpMethod.POST, "/**").hasAnyAuthority(RoleEnum.USER.toString(), RoleEnum.ADMIN.toString())
            //.antMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority(RoleEnum.USER.toString(), RoleEnum.ADMIN.toString())
            // Defaults
            .anyRequest().authenticated()
            //.and().httpBasic()
            .and().csrf().disable();

    }

}
