package com.elsynergy.nigerianpostcodes.service.accountentities;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.*;
import com.elsynergy.nigerianpostcodes.model.request.AccountIpAccessRequest;
import com.elsynergy.nigerianpostcodes.model.request.AccountSubscribeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.repo.accountentities.*;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;
import com.elsynergy.nigerianpostcodes.web.exception.BadRequestException;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private AccountIpAccessRepository accountIpAccessRepository;

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
        final Account existingAccount = this.getVerifiedAccount(request.getAccountName());

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

        final Account savedAccount = this.accountRepository.save(existingAccount);

        return this.accountResponseMapper.map(savedAccount);
    }

    public void linkIpAccess(final AccountIpAccessRequest request) throws ResourceNotFoundException
    {
        final Account existingAccount = this.getVerifiedAccount(request.getAccountName());

        final Set<AccountIpAccess> toBeSavedAccountIpAccesses = this.filterIpAddressList(request,
                existingAccount).get("toBeSaved");

        this.accountIpAccessRepository.save(toBeSavedAccountIpAccesses);
    }

    public void unlinkIpAccess(final AccountIpAccessRequest request) throws ResourceNotFoundException
    {
        final Account existingAccount = this.getVerifiedAccount(request.getAccountName());

        final Set<AccountIpAccess> toBeDeletedaccountIpAccesses = this.filterIpAddressList(request,
                existingAccount).get("toBeDeleted");

        this.accountIpAccessRepository.delete(toBeDeletedaccountIpAccesses);
    }

    /**
     * Groups ipAddresses into
     * to be saved
     * and to be deleted.
     *
     * @param requestedIpAddressList
     * @param existingAccount
     * @return
     */
    private Map<String, Set<AccountIpAccess>> filterIpAddressList(final AccountIpAccessRequest request, final Account existingAccount)
    {
        final Set<AccountIpAccess> toBeDeletedAccountIpAccesses = new HashSet<>();
        final Set<AccountIpAccess> toBeSavedAccountIpAccesses = new HashSet<>();
        final Map<String, Set<AccountIpAccess>> groupedIpAccessMap = new HashMap<>();

        final List<String> requestedIpAddressList = Arrays.asList(request.getAllowedIpAddresses()
                .split(","));
        final List<String> existingIpAddressList = new ArrayList<>();
        final Set<AccountIpAccess> existingAccountIpAccesses = existingAccount.getAccountIpAccesses();
        if (existingAccountIpAccesses != null) {
            for (final AccountIpAccess accountIpAccess : existingAccountIpAccesses) {
                existingIpAddressList.add(accountIpAccess.getIpAddress());
                if (requestedIpAddressList.contains(accountIpAccess.getIpAddress())) {
                    toBeDeletedAccountIpAccesses.add(accountIpAccess);
                }
            }
        }

        for (final String requestedIpAddress : requestedIpAddressList) {
            if (!existingIpAddressList.contains(requestedIpAddress)) {
                final AccountIpAccess accountIpAccess = new AccountIpAccess();
                accountIpAccess.setIpAddress(requestedIpAddress);
                accountIpAccess.setAccount(existingAccount);
                toBeSavedAccountIpAccesses.add(accountIpAccess);
            }
        }

        groupedIpAccessMap.put("toBeSaved", toBeSavedAccountIpAccesses);
        groupedIpAccessMap.put("toBeDeleted", toBeDeletedAccountIpAccesses);

        return groupedIpAccessMap;
    }

    public void updateRequestCount(final Long accountId)
    {
        final Account account = this.accountRepository.findOne(accountId);
        final AccountSubscription accountSubscription = account.getAccountSubscription();

        if (accountSubscription != null) {
            final int numberOfRequestsMade = accountSubscription.getNumberOfRequestsMade();
            accountSubscription.setNumberOfRequestsMade(numberOfRequestsMade + 1);

            this.subscriptionRepository.save(accountSubscription);
        }

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
        account.getAccountSubscription().setNumberOfRequestsMade(0);
        account.getAccountSubscription().setStartDate(startDate);
        account.getAccountSubscription().setEndDate(endDate);
        account.getAccountSubscription().setExpired(false);
    }

    private Account getVerifiedAccount(final String accountName) throws ResourceNotFoundException
    {
        final Optional<Account> account = this.getAccountByName(accountName);

        if (!account.isPresent()) {
            throw new ResourceNotFoundException(String.format("Account with name '%s' not found.", accountName));
        }

        return account.get();
    }

    private String getNextAccountKey()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
