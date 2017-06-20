package com.elsynergy.nigerianpostcodes.mapper;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Account;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.PackageType;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Privilege;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Subscription;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse.SubscriptionDetails;
import com.elsynergy.nigerianpostcodes.repo.accountentities.SubscriptionRepository;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountResponseMapper implements IResponseMapper<Account, AccountResponse>
{
    @Autowired
    private SubscriptionRepository subscriptionRepo;

    @Autowired
    private DateTimeService dateTimeService;

    @Override
    public AccountResponse map(final Account toMap)
    {
        final AccountResponse accountResponse = new AccountResponse();
        accountResponse.setUsername(toMap.getName());
        accountResponse.setActive(toMap.getActive());
        accountResponse.setAccountKey(toMap.getAccountKey());

        final PackageType packageType = toMap.getPackageType();
        accountResponse.setPackageType(packageType.getName());

        accountResponse.setRole(toMap.getRole().getName());

        final List<String> privilegeList = new ArrayList<>();

        for (final Privilege privilege : packageType.getPrivilegeSet()) {
            privilegeList.add(privilege.getName());
        }

        accountResponse.setPrivileges(privilegeList);

        final Optional<Subscription> subscription = this.subscriptionRepo.findOneByAccountId(toMap.getId());

        if (subscription.isPresent()) {
            final Subscription subscriptionObj = subscription.get();
            final SubscriptionDetails subscriptionDetails = accountResponse.new SubscriptionDetails();
            subscriptionDetails.setDurationInMonths(subscriptionObj.getDurationInMonths());
            subscriptionDetails.setNumberOfRequestsAllowed(subscriptionObj.getNumberOfRequestsAllowed());
            subscriptionDetails.setNumberOfRequestsMade(subscriptionObj.getNumberOfRequestsMade());
            subscriptionDetails.setStartDate(this.dateTimeService.getStringFormattedDate(subscriptionObj.getStartDate()));
            subscriptionDetails.setEndDate(this.dateTimeService.getStringFormattedDate(subscriptionObj.getEndDate()));
            subscriptionDetails.setExpired(subscriptionObj.isExpired());
            accountResponse.setSubscriptionDetails(subscriptionDetails);
        }

        return accountResponse;
    }

}
