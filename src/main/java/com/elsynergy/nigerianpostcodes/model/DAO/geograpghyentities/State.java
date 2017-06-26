package com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities;

import java.util.Set;

import javax.persistence.*;

/**
*
* @author silver.ibenye
*
*/
@Entity
@Table(name = "states")
public class State
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy="state")
    private Set<LocalGovernmentArea> localGovernmentAreas;

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

    public String getCode()
    {
        return this.code;
    }

    public void setCode(final String code)
    {
        this.code = code;
    }

    public Set<LocalGovernmentArea> getLocalGovernmentAreas()
    {
        return this.localGovernmentAreas;
    }

    public void setLocalGovernmentAreas(final Set<LocalGovernmentArea> localGovernmentAreas)
    {
        this.localGovernmentAreas = localGovernmentAreas;
    }

}
