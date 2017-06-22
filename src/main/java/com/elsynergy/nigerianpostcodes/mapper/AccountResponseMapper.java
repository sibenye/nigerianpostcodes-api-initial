package com.elsynergy.nigerianpostcodes.mapper;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Account;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.AccountIpAccess;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.PackageType;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Privilege;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse.SubscriptionDetails;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountResponseMapper implements IResponseMapper<Account, AccountResponse>
{
    @Autowired
    private DateTimeService dateTimeService;

    @Override
    public AccountResponse map(final Account toMap)
    {
        final AccountResponse accountResponse = new AccountResponse();
        accountResponse.setUsername(toMap.getName());
        accountResponse.setActive(toMap.getActive());
        accountResponse.setAccountKey(toMap.getAccountKey());

        accountResponse.setRole(toMap.getRole().getName());


        if (toMap.getPackageType() != null) {
            final PackageType packageType = toMap.getPackageType();
            accountResponse.setPackageType(packageType.getName());
            final List<String> privilegeList = new ArrayList<>();

            for (final Privilege privilege : packageType.getPrivilegeSet()) {
                privilegeList.add(privilege.getName());
            }
            accountResponse.setPrivileges(privilegeList);
        }

        if (toMap.getAccountSubscription() != null) {
            final SubscriptionDetails subscriptionDetails = accountResponse.new SubscriptionDetails();
            subscriptionDetails.setDurationInMonths(toMap.getAccountSubscription().getDurationInMonths());
            subscriptionDetails.setNumberOfRequestsAllowed(toMap.getAccountSubscription().getNumberOfRequestsAllowed());
            subscriptionDetails.setNumberOfRequestsMade(toMap.getAccountSubscription().getNumberOfRequestsMade());
            subscriptionDetails.setStartDate(this.dateTimeService.getStringFormattedDate(toMap.getAccountSubscription().getStartDate()));
            subscriptionDetails.setEndDate(this.dateTimeService.getStringFormattedDate(toMap.getAccountSubscription().getEndDate()));
            subscriptionDetails.setExpired(toMap.getAccountSubscription().isExpired());
            accountResponse.setSubscriptionDetails(subscriptionDetails);
        }

        if (toMap.getAccountIpAccesses() != null) {
            final List<String> allowedIpAddresses = new ArrayList<>();

            for (final AccountIpAccess accountIpAccess : toMap.getAccountIpAccesses()) {
                allowedIpAddresses.add(accountIpAccess.getIpAddress());
            }

            accountResponse.setAllowedIpAddresses(allowedIpAddresses);
        }

        return accountResponse;
    }

}
