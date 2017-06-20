package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;

import org.springframework.security.core.authority.AuthorityUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CurrentAccountDetails extends org.springframework.security.core.userdetails.User
{


    private boolean active;
    private String packageType;
    private String role;
    private boolean accountSubscriptionExpired;
    private List<String> privileges = new ArrayList<>();

    public CurrentAccountDetails(final Account account)
    {
        super(account.getName(),
                account.getAccountKey(),
                AuthorityUtils.createAuthorityList(account.getRole().getName()));
        this.active = account.getActive();
        this.role = account.getRole().getName();

        for (final Privilege privilege : account.getPackageType().getPrivilegeSet())
        {
            this.privileges.add(privilege.getName());
        }

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
        return !this.accountSubscriptionExpired;
    }

    public void setAccountExpired(final Boolean accountExpired)
    {
        this.accountSubscriptionExpired = accountExpired;
    }

}
