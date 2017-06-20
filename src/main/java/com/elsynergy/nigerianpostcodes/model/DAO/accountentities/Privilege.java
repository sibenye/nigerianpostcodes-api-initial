package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;

import java.util.Set;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "privileges")
public class Privilege extends Audit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy="privileges")
    private Set<PackageType> packageTypes;

    public Privilege() {}

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

    public Set<PackageType> getPackageTypes()
    {
        return this.packageTypes;
    }

    public void setPackageTypes(final Set<PackageType> packages)
    {
        this.packageTypes = packages;
    }

}
