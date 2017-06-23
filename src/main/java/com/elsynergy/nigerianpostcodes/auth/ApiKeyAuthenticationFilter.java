package com.elsynergy.nigerianpostcodes.auth;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.CurrentAccountDetails;
import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;
import com.elsynergy.nigerianpostcodes.model.enums.RoleEnum;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiKeyAuthenticationFilter extends DigestAuthenticationFilter
{
    private final UserCache userCache = new NullUserCache();
    private final CurrentUserDetailsService currentUserDetailsService;
    private final DateTimeService dateTimeService;

    private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public ApiKeyAuthenticationFilter(final CurrentUserDetailsService currentUserDetailsService)
    {
        this.currentUserDetailsService = currentUserDetailsService;
        this.dateTimeService = new DateTimeService();
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException
    {
        // get the API key from header
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        final String apiKey = request.getHeader("NPC-API-KEY");

        if (apiKey == null) {
            chain.doFilter(request, response);

            return;
        }

        // lookup account by api key
        CurrentAccountDetails currentUserDetails = (CurrentAccountDetails) this.userCache.getUserFromCache(apiKey);

        if (currentUserDetails == null) {
            currentUserDetails = (CurrentAccountDetails) this.currentUserDetailsService.loadUserByUsername(apiKey);

            if (currentUserDetails == null) {
                throw new AuthenticationServiceException("Invalid API Key.");
            }

            this.userCache.putUserInCache(currentUserDetails);
        }

        // enforce access rules
        this.enforceAccessRules(currentUserDetails, request);

        //update request count
        this.currentUserDetailsService.updateRequestCount();

        final Authentication authentication = this.createSuccessfulAuthentication(request, currentUserDetails);
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        chain.doFilter(request, response);

    }

    private void enforceAccessRules(final CurrentAccountDetails currentUserDetails, final HttpServletRequest request)
    {

        // verify account is active
        if (!currentUserDetails.isEnabled()) {
            throw new AuthenticationServiceException("Account is disabled.");
        }

        // verify account subscription is not expired
        if (!currentUserDetails.isAccountNonExpired()) {
            throw new AuthenticationServiceException("Account subscription is expired.");
        }

        // verify allowed ipAddresses
        final String remoteAddr = request.getRemoteAddr();
        final String host = request.getRemoteHost();
        if (!currentUserDetails.getAllowedIpAddresses().isEmpty()
                && !currentUserDetails.getAllowedIpAddresses().contains(request.getRemoteAddr())) {
            throw new AuthenticationServiceException("This client ip is NOT authorized to use this API Key. Expecting: " + request.getRemoteAddr() + " or " + request.getRemoteHost());
        }

        // verify account subscription end date is in the future
        final Date today = this.dateTimeService.getCurrentDateAndTime().getTime();
        if (currentUserDetails.getSubscriptionEndDate().before(today)
                && !this.accountIsAutoRenewable(currentUserDetails)) {
            throw new AuthenticationServiceException("Account subscription has ended, renew subscription.");
        }

        // verify allowed request count has not been exceeded
        if (currentUserDetails.getNumberOfRequestsMade()
                > currentUserDetails.getNumberOfRequestsAllowed()
                && !currentUserDetails.hasUnlimitedRequest()
                && !this.accountIsAnException(currentUserDetails)) {
            throw new AuthenticationServiceException("This client has exceeded its request limit");
        }

        if ((currentUserDetails.getSubscriptionEndDate().before(today)
                && this.accountIsAutoRenewable(currentUserDetails))
                || (currentUserDetails.getNumberOfRequestsMade() > currentUserDetails.getNumberOfRequestsAllowed()
                        && this.accountIsAnException(currentUserDetails))) {
            // implicitly renew subscription
            this.currentUserDetailsService.renewSubscription();

        }

    }

    private boolean accountIsAnException(final CurrentAccountDetails currentUserDetails)
    {
        boolean isAnException = false;

        if (currentUserDetails.getRole().equalsIgnoreCase(RoleEnum.ADMIN.toString())
                || currentUserDetails.getPackageType().equalsIgnoreCase(PackageEnum.DOC_VIEW.toString())) {
            isAnException = true;
        }

        return isAnException;
    }

    private boolean accountIsAutoRenewable(final CurrentAccountDetails currentUserDetails)
    {
        boolean isAutoRenewable = false;

        if (currentUserDetails.getRole().equalsIgnoreCase(RoleEnum.ADMIN.toString())
                || currentUserDetails.getPackageType().equalsIgnoreCase(PackageEnum.DOC_VIEW.toString())
                || currentUserDetails.getPackageType().equalsIgnoreCase(PackageEnum.BASIC.toString())) {
            isAutoRenewable = true;
        }

        return isAutoRenewable;
    }

    private Authentication createSuccessfulAuthentication(final HttpServletRequest request, final UserDetails user)
    {
        UsernamePasswordAuthenticationToken authRequest;
        authRequest = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));

        return authRequest;
    }

}
