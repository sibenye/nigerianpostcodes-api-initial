package com.elsynergy.nigerianpostcodes.model.DAO.userentities;

import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "packages")
public class Package
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PackageEnum name;

    @Column(name = "allowedMonthlyRequests", nullable = true)
    private Integer allowedMonthlyRequests;

    @Column(name = "unlimitedRequests")
    private Boolean unlimitedRequests;

    @Column(name = "allowExtraRequests")
    private Boolean allowExtraRequests;

    @Column(name = "dateCreated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "dateModified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    @ManyToMany(targetEntity=Feature.class)
    private Set<Feature> featureSet;

    public Package() {}

    public Integer getId()
    {
        return this.id;
    }

    public void setId(final Integer id)
    {
        this.id = id;
    }

    public com.elsynergy.nigerianpostcodes.model.enums.PackageEnum getName()
    {
        return this.name;
    }

    public void setName(final com.elsynergy.nigerianpostcodes.model.enums.PackageEnum name)
    {
        this.name = name;
    }

    public Integer getAllowedMonthlyRequests()
    {
        return this.allowedMonthlyRequests;
    }

    public void setAllowedMonthlyRequests(final Integer allowedMonthlyRequests)
    {
        this.allowedMonthlyRequests = allowedMonthlyRequests;
    }

    public Boolean getUnlimitedRequests()
    {
        return this.unlimitedRequests;
    }

    public void setUnlimitedRequests(final Boolean unlimitedRequests)
    {
        this.unlimitedRequests = unlimitedRequests;
    }

    public Boolean getAllowExtraRequests()
    {
        return this.allowExtraRequests;
    }

    public void setAllowExtraRequests(final Boolean allowExtraRequests)
    {
        this.allowExtraRequests = allowExtraRequests;
    }

    public Date getDateCreated()
    {
        return this.dateCreated;
    }

    public void setDateCreated(final Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified()
    {
        return this.dateModified;
    }

    public void setDateModified(final Date dateModified)
    {
        this.dateModified = dateModified;
    }

    public Set<Feature> getFeatureSet()
    {
        return this.featureSet;
    }

    public void setFeatureSet(final Set<Feature> featureSet)
    {
        this.featureSet = featureSet;
    }

}
