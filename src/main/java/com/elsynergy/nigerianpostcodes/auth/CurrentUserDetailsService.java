package com.elsynergy.nigerianpostcodes.auth;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Account;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.CurrentAccountDetails;
import com.elsynergy.nigerianpostcodes.service.accountentities.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
@Component
public class CurrentUserDetailsService implements UserDetailsService
{
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(final String username)
    {
        final Optional<Account> user = this.accountService.getAccountByKey(username);

        if (!user.isPresent()) {
            return null;
        }
        return new CurrentAccountDetails(user.get());
    }

}
