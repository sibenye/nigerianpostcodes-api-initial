package com.elsynergy.nigerianpostcodes.model.response;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public class AccountResponse
{
    private String accountName;

    private Boolean active;

    private String accountKey;

    private String packageType;

    private String role;

    private List<String> privileges;

    private SubscriptionDetails subscriptionDetails;

    public class SubscriptionDetails
    {
        private boolean isExpired;

        private Integer durationInMonths;

        private Integer numberOfRequestsAllowed;

        private Integer numberOfRequestsMade;

        private String startDate;

        private String endDate;

        public boolean isExpired()
        {
            return this.isExpired;
        }

        public void setExpired(final boolean isExpired)
        {
            this.isExpired = isExpired;
        }

        public Integer getDurationInMonths()
        {
            return this.durationInMonths;
        }

        public void setDurationInMonths(final Integer durationInMonths)
        {
            this.durationInMonths = durationInMonths;
        }

        public Integer getNumberOfRequestsAllowed()
        {
            return this.numberOfRequestsAllowed;
        }

        public void setNumberOfRequestsAllowed(final Integer numberOfRequestsAllowed)
        {
            this.numberOfRequestsAllowed = numberOfRequestsAllowed;
        }

        public Integer getNumberOfRequestsMade()
        {
            return this.numberOfRequestsMade;
        }

        public void setNumberOfRequestsMade(final Integer numberOfRequestsMade)
        {
            this.numberOfRequestsMade = numberOfRequestsMade;
        }

        public String getStartDate()
        {
            return this.startDate;
        }

        public void setStartDate(final String startDate)
        {
            this.startDate = startDate;
        }

        public String getEndDate()
        {
            return this.endDate;
        }

        public void setEndDate(final String endDate)
        {
            this.endDate = endDate;
        }
    }

    public String getAccountName()
    {
        return this.accountName;
    }

    public void setUsername(final String username)
    {
        this.accountName = username;
    }

    public Boolean getActive()
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

    public List<String> getPrivileges()
    {
        return this.privileges;
    }

    public void setPrivileges(final List<String> privileges)
    {
        this.privileges = privileges;
    }

    public String getRole()
    {
        return this.role;
    }

    public void setRole(final String role)
    {
        this.role = role;
    }

    public String getAccountKey()
    {
        return this.accountKey;
    }

    public void setAccountKey(final String accountKey)
    {
        this.accountKey = accountKey;
    }

    public SubscriptionDetails getSubscriptionDetails()
    {
        return this.subscriptionDetails;
    }

    public void setSubscriptionDetails(final SubscriptionDetails subscriptionDetails)
    {
        this.subscriptionDetails = subscriptionDetails;
    }

}
