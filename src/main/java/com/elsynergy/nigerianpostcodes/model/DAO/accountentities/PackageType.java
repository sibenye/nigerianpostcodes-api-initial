package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;

import java.util.Set;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "packagetypes")
public class PackageType extends Audit
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="package_privilege_links",
            joinColumns=
                @JoinColumn(name="packageid", referencedColumnName="id"),
            inverseJoinColumns=
                @JoinColumn(name="privilegeid", referencedColumnName="id")
        )
    private Set<Privilege> privileges;

    public PackageType() {}

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

    public Set<Privilege> getPrivilegeSet()
    {
        return this.privileges;
    }

    public void setPrivilegeSet(final Set<Privilege> privilegeSet)
    {
        this.privileges = privilegeSet;
    }

}
