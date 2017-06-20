package com.elsynergy.nigerianpostcodes.auth;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.CurrentAccountDetails;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiKeyAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private final UserCache userCache = new NullUserCache();
    private final CurrentUserDetailsService currentUserDetailsService;

    public ApiKeyAuthenticationFilter(final CurrentUserDetailsService currentUserDetailsService)
    {
        this.currentUserDetailsService = currentUserDetailsService;
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException
    {
        //get the API key from header
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        final String apiKey = request.getHeader("NPC-API-KEY");

        if (apiKey == null) {
            chain.doFilter(request, response);

            return;
        }

        //lookup account by api key
        CurrentAccountDetails user = (CurrentAccountDetails) this.userCache.getUserFromCache(apiKey);

        if (user == null) {
            user = (CurrentAccountDetails) this.currentUserDetailsService
                    .loadUserByUsername(apiKey);

            if (user == null) {
                throw new AuthenticationServiceException(
                        "Invalid API Key");
            }

            this.userCache.putUserInCache(user);
        }

        //TODO: verify allowed ipAddresses

        final Authentication authentication = this.createSuccessfulAuthentication(request, user);
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        chain.doFilter(request, response);

    }

    private Authentication createSuccessfulAuthentication(final HttpServletRequest request,
            final UserDetails user) {
        UsernamePasswordAuthenticationToken authRequest;
        authRequest = new UsernamePasswordAuthenticationToken(user,
                user.getPassword(), user.getAuthorities());
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));

        return authRequest;
    }


}
