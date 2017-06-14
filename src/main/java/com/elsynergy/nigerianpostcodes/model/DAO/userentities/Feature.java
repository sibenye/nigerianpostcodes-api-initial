package com.elsynergy.nigerianpostcodes.model.DAO.userentities;

import java.util.Set;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "features")
public class Feature extends Audit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy="features")
    private Set<Package> packages;

    public Feature() {}

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

    public Set<Package> getPackages()
    {
        return this.packages;
    }

    public void setPackages(final Set<Package> packages)
    {
        this.packages = packages;
    }

}
