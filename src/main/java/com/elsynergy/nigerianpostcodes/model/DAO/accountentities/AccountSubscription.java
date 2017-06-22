package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;


import java.util.Date;

import javax.persistence.*;

/**
*
* @author silver.ibenye
*
*/
@Entity
@Table(name = "account_subscriptions")
public class AccountSubscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "durationinmonths", nullable = false)
    private Integer durationInMonths;

    @Column(name = "numberofrequestsallowed")
    private Integer numberOfRequestsAllowed;

    @Column(name = "numberofrequestsmade")
    private Integer numberOfRequestsMade;

    @Column(name = "startdate", nullable = false)
    private Date startDate;

    @Column(name = "enddate", nullable = false)
    private Date endDate;

    @Column(name = "expired")
    private boolean isExpired;

    public AccountSubscription()
    {

    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(final Long id)
    {
        this.id = id;
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

    public Date getStartDate()
    {
        return this.startDate;
    }

    public void setStartDate(final Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return this.endDate;
    }

    public void setEndDate(final Date endDate)
    {
        this.endDate = endDate;
    }

    public boolean isExpired()
    {
        return this.isExpired;
    }

    public void setExpired(final boolean isExpired)
    {
        this.isExpired = isExpired;
    }

}
