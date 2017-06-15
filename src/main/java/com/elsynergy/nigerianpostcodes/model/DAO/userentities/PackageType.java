package com.elsynergy.nigerianpostcodes.model.DAO.userentities;

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
    private String name;

    @Column(name = "allowedmonthlyrequests", nullable = true)
    private Integer allowedMonthlyRequests;

    @Column(name = "unlimitedrequests")
    private Boolean unlimitedRequests;

    @Column(name = "allowextrarequests")
    private Boolean allowExtraRequests;

    @ManyToMany
    @JoinTable(
            name="package_feature_links",
            joinColumns=
                @JoinColumn(name="packageid", referencedColumnName="id"),
            inverseJoinColumns=
                @JoinColumn(name="featureid", referencedColumnName="id")
        )
    private Set<Feature> features;

    public Package() {}

    public Integer getId()
    {
        return this.id;
    }

    public void setId(final Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(final String name)
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
        return this.features;
    }

    public void setFeatureSet(final Set<Feature> featureSet)
    {
        this.features = featureSet;
    }

}
