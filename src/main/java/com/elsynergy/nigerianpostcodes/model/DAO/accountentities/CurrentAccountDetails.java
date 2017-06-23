package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;

import org.springframework.security.core.authority.AuthorityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class CurrentAccountDetails extends org.springframework.security.core.userdetails.User
{

    private Long accountId;
    private boolean active;
    private String packageType;
    private String role;
    private boolean subscriptionExpired;
    private List<String> privileges = new ArrayList<>();
    private Date subscriptionEndDate;
    private List<String> allowedIpAddresses = new ArrayList<>();
    private int numberOfRequestsMade;
    private int numberOfRequestsAllowed;
    private boolean hasUnlimitedRequest;

    public CurrentAccountDetails(final Account account)
    {
        super(account.getName(),
                account.getAccountKey(),
                AuthorityUtils.createAuthorityList(account.getRole().getName()));
        this.accountId = account.getId();
        this.active = account.getActive();
        this.role = account.getRole().getName();
        this.packageType = account.getPackageType().getName();
        this.subscriptionExpired = account.getAccountSubscription().isExpired();
        this.subscriptionEndDate = account.getAccountSubscription().getEndDate();
        this.numberOfRequestsMade = account.getAccountSubscription().getNumberOfRequestsMade() != null
                ? account.getAccountSubscription().getNumberOfRequestsMade() : 0;
        this.numberOfRequestsAllowed = account.getAccountSubscription().getNumberOfRequestsAllowed() != null
                ? account.getAccountSubscription().getNumberOfRequestsAllowed() : 0;
        this.hasUnlimitedRequest = account.getPackageType().getUnlimitedRequests();

        for (final Privilege privilege : account.getPackageType().getPrivilegeSet())
        {
            this.privileges.add(privilege.getName());
        }

        final Set<AccountIpAccess> accountIpAccesses = account.getAccountIpAccesses();
        if (accountIpAccesses != null) {
            for (final AccountIpAccess accountIpAccess : accountIpAccesses) {
                this.allowedIpAddresses.add(accountIpAccess.getIpAddress());
            }
        }

    }

    public Long getAccountId()
    {
        return this.accountId;
    }

    public void setAccountId(final Long accountId)
    {
        this.accountId = accountId;
    }

    public List<String> getPrivileges()
    {
        return this.privileges;
    }

    public void setPrivileges(final List<String> features)
    {
        this.privileges = features;
    }

    public String getRole()
    {
        return this.role;
    }

    public void setRole(final String role)
    {
        this.role = role;
    }

    @Override
    public boolean isEnabled()
    {
        return this.active;
    }

    public void setActive(final Boolean active)
    {
        this.active = active;
    }

    public String getPackageType()
    {
        return this.packageType;
    }

    public void setPackageType(final String packageType)
    {
        this.packageType = packageType;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return !this.subscriptionExpired;
    }

    public void setAccountExpired(final Boolean accountExpired)
    {
        this.subscriptionExpired = accountExpired;
    }

    public Date getSubscriptionEndDate()
    {
        return this.subscriptionEndDate;
    }

    public void setSubscriptionEndDate(final Date subscriptionEndDate)
    {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public List<String> getAllowedIpAddresses()
    {
        return this.allowedIpAddresses;
    }

    public void setAllowedIpAddresses(final List<String> allowedIpAddresses)
    {
        this.allowedIpAddresses = allowedIpAddresses;
    }

    public int getNumberOfRequestsMade()
    {
        return this.numberOfRequestsMade;
    }

    public void setNumberOfRequestsMade(final int numberOfRequestsMade)
    {
        this.numberOfRequestsMade = numberOfRequestsMade;
    }

    public int getNumberOfRequestsAllowed()
    {
        return this.numberOfRequestsAllowed;
    }

    public void setNumberOfRequestsAllowed(final int numberOfRequestsAllowed)
    {
        this.numberOfRequestsAllowed = numberOfRequestsAllowed;
    }

    public boolean hasUnlimitedRequest()
    {
        return this.hasUnlimitedRequest;
    }

    public void setHasUnlimitedRequest(final boolean hasUnlimitedRequest)
    {
        this.hasUnlimitedRequest = hasUnlimitedRequest;
    }

}
