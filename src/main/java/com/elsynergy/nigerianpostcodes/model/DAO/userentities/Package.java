package com.elsynergy.nigerianpostcodes.model.DAO.userentities;

import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;

import java.util.Set;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "packages")
public class Package extends Audit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PackageEnum name;

    @Column(name = "allowedmonthlyrequests", nullable = true)
    private Integer allowedMonthlyRequests;

    @Column(name = "unlimitedrequests")
    private Boolean unlimitedRequests;

    @Column(name = "allowextrarequests")
    private Boolean allowExtraRequests;

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

    public Set<Feature> getFeatureSet()
    {
        return this.featureSet;
    }

    public void setFeatureSet(final Set<Feature> featureSet)
    {
        this.featureSet = featureSet;
    }

}
