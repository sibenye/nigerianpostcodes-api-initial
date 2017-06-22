package com.elsynergy.nigerianpostcodes.auth;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Account;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.CurrentAccountDetails;
import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;
import com.elsynergy.nigerianpostcodes.model.request.AccountSubscribeRequest;
import com.elsynergy.nigerianpostcodes.service.accountentities.AccountService;
import com.elsynergy.nigerianpostcodes.web.exception.BadRequestException;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

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

    private CurrentAccountDetails currentUserDetails;

    @Override
    public UserDetails loadUserByUsername(final String username)
    {
        final Optional<Account> user = this.accountService.getAccountByKey(username);

        if (!user.isPresent()) {
            this.currentUserDetails = null;
            return null;
        }
        this.currentUserDetails = new CurrentAccountDetails(user.get());

        return this.currentUserDetails;
    }

    public void renewSubscription()
    {
        final AccountSubscribeRequest accountSubscribeRequest = new AccountSubscribeRequest();
        accountSubscribeRequest.setAccountName(this.currentUserDetails.getUsername());
        accountSubscribeRequest.setPackageName(PackageEnum.valueOf(this.currentUserDetails.getPackageType()));
        accountSubscribeRequest.setRenewSubscription(true);

        try {
            this.accountService.subscribeAccount(accountSubscribeRequest);
        } catch (final ResourceNotFoundException e) {
            // TODO log error message
        } catch (final BadRequestException e) {
            // TODO log error message
        }
    }

}
