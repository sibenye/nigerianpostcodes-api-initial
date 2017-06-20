package com.elsynergy.nigerianpostcodes.service.accountentities;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Account;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.PackageType;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Role;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Subscription;
import com.elsynergy.nigerianpostcodes.model.request.AccountSubscribeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.repo.accountentities.AccountRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.RoleRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.SubscriptionRepository;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * User Service Class.
 *
 * @author silver.ibenye
 *
 */
@Service
public class AccountService
{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AccountResponseMapper accountResponseMapper;

    @Autowired
    private DateTimeService dateTimeService;

    public Optional<Account> getAccountByName(final String acctName)
    {
        final Optional<Account> account = this.accountRepository.findOneByName(acctName);

        return account;
    }

    public Optional<Account> getAccountByKey(final String acctKey)
    {
        final Optional<Account> account = this.accountRepository.findOneByAccountKey(acctKey);

        return account;
    }

    public AccountResponse getAccountDetails(final String acctName) throws ResourceNotFoundException
    {
        final Optional<Account> account = this.getAccountByName(acctName);

        if (!account.isPresent()) {
            throw new ResourceNotFoundException("Account Not Found");
        }

        return this.accountResponseMapper.map(account.get());
    }

    public AccountResponse registerAccount(final RegisterAccountRequest request)
    {
        final Account account = new Account(request);
        account.setAccountKey(this.getNextAccountKey());

        final PackageType packageType = this.packageRepository.findOneByName(request.getPackageName().toString()).get();
        account.setPackageType(packageType);

        final Role role = this.roleRepository.findOneByName(request.getRole().toString()).get();
        account.setRole(role);

        final Account registeredAccount =  this.accountRepository.save(account);
        return this.accountResponseMapper.map(registeredAccount);

    }

    public AccountResponse subscribeAccount(final AccountSubscribeRequest request) throws ResourceNotFoundException
    {
        //verify account name
        final Optional<Account> account = this.accountRepository.findOneByName(request.getAccountName());

        if (!account.isPresent()) {
            throw new ResourceNotFoundException(String.format("Account with name '%s' not found.", request.getAccountName()));
        }

        final Account existingAccount = account.get();
        final Integer durationInMonths = request.getDurationInMonths();
        final Integer allowedMonthlyRequests = existingAccount.getPackageType().getAllowedMonthlyRequests();
        final Integer numberOfAllowedRequests = allowedMonthlyRequests * durationInMonths;

        final Calendar currentCalendar = this.dateTimeService.getCurrentDateAndTime();
        final Date startDate = currentCalendar.getTime();
        currentCalendar.add(Calendar.MONTH, durationInMonths);
        final Date endDate = currentCalendar.getTime();

        final Subscription subscription = new Subscription();
        subscription.setAccount(existingAccount);
        subscription.setDurationInMonths(durationInMonths);
        subscription.setNumberOfRequestsAllowed(numberOfAllowedRequests);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);

        this.subscriptionRepository.save(subscription);

        return this.accountResponseMapper.map(existingAccount);
    }

    private String getNextAccountKey()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
