package com.elsynergy.nigerianpostcodes.auth;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.CurrentAccountDetails;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class NPCAuthenticationProvider implements AuthenticationProvider
{
    private final CurrentUserDetailsService userDetailsService;

    public NPCAuthenticationProvider(final CurrentUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException
    {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final CurrentAccountDetails user = (CurrentAccountDetails) this.userDetailsService.loadUserByUsername(username);

        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("User not found.");
        }

        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationCredentialsNotFoundException("Invalid credentials.");
        }

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(final Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
