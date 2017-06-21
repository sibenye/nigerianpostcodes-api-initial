package com.elsynergy.nigerianpostcodes.service.accountentities;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Account;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.AccountSubscription;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.PackageType;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Role;
import com.elsynergy.nigerianpostcodes.model.request.AccountSubscribeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.repo.accountentities.AccountRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.RoleRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.SubscriptionRepository;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;
import com.elsynergy.nigerianpostcodes.web.exception.BadRequestException;
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

    public AccountResponse registerAccount(final RegisterAccountRequest request) throws ResourceNotFoundException
    {
        final Account account = new Account(request);
        account.setAccountKey(this.getNextAccountKey());

        final Optional<Role> requestedRole = this.roleRepository.findOneByName(request.getRole().toString());

        if (!requestedRole.isPresent()) {
            throw new ResourceNotFoundException(String.format("Role with name '%s' not found.", request.getRole()));
        }

        if (request.getPackageName() != null) {
            final Optional<PackageType> requestedPackageType = this.packageRepository.findOneByName(request.getPackageName().toString());

            if (!requestedPackageType.isPresent()) {
                throw new ResourceNotFoundException(String.format("PackageType with name '%s' not found.", request.getPackageName()));
            }

            account.setPackageType(requestedPackageType.get());
        }

        if (request.getPackageName() != null
                && request.getDurationInMonths() != null) {
             this.setSubscriptionDetails(account, request.getDurationInMonths());
             final AccountSubscription savedSubscription =  this.subscriptionRepository.save(account.getAccountSubscription());

             account.setAccountSubscription(savedSubscription);
        }

        account.setRole(requestedRole.get());

        final Account registeredAccount =  this.accountRepository.save(account);


        return this.accountResponseMapper.map(registeredAccount);

    }

    public AccountResponse subscribeAccount(final AccountSubscribeRequest request) throws ResourceNotFoundException, BadRequestException
    {
        //verify account name
        final Optional<Account> account = this.accountRepository.findOneByName(request.getAccountName());

        if (!account.isPresent()) {
            throw new ResourceNotFoundException(String.format("Account with name '%s' not found.", request.getAccountName()));
        }

        final Account existingAccount = account.get();

        final Optional<PackageType> requestedPackageType = this.packageRepository.findOneByName(request.getPackageName().toString());

        if (!requestedPackageType.isPresent()) {
            throw new ResourceNotFoundException(String.format("PackageType with name '%s' not found.", request.getPackageName()));
        }

        if (!request.isRenewSubscription()
                && existingAccount.getPackageType() != null
                && existingAccount.getPackageType().getId() == requestedPackageType.get().getId()
                && existingAccount.getAccountSubscription() != null
                && !existingAccount.getAccountSubscription().isExpired()) {
            throw new BadRequestException("Subscription exists and has not expired.");
        }

        if (existingAccount.getPackageType() == null
                || existingAccount.getPackageType().getId() != requestedPackageType.get().getId()) {
            existingAccount.setPackageType(requestedPackageType.get());
        }

        this.setSubscriptionDetails(existingAccount, request.getDurationInMonths());
        final AccountSubscription savedSubscription =  this.subscriptionRepository.save(existingAccount.getAccountSubscription());

        existingAccount.setAccountSubscription(savedSubscription);

        final Account savedAccount =  this.accountRepository.save(existingAccount);

        return this.accountResponseMapper.map(savedAccount);
    }

    private void setSubscriptionDetails(final Account account, final Integer durationInMonths)
    {
        if (account.getAccountSubscription() == null) {
            final AccountSubscription subscription = new AccountSubscription();
            account.setAccountSubscription(subscription);
        }

        final Integer allowedMonthlyRequests = account.getPackageType().getAllowedMonthlyRequests();
        final Integer numberOfAllowedRequests = allowedMonthlyRequests * durationInMonths;

        final Calendar currentCalendar = this.dateTimeService.getCurrentDateAndTime();
        final Date startDate = currentCalendar.getTime();
        currentCalendar.add(Calendar.MONTH, durationInMonths);
        final Date endDate = currentCalendar.getTime();

        account.getAccountSubscription().setDurationInMonths(durationInMonths);
        account.getAccountSubscription().setNumberOfRequestsAllowed(numberOfAllowedRequests);
        account.getAccountSubscription().setStartDate(startDate);
        account.getAccountSubscription().setEndDate(endDate);
        account.getAccountSubscription().setExpired(false);
    }

    private String getNextAccountKey()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
