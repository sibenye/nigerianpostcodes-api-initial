package com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities;

import javax.persistence.*;

/**
*
* @author silver.ibenye
*
*/
@Entity
@Table(name = "lgas")
public class LocalGovernmentArea
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional=false)
    @JoinColumn(name="stateid", nullable=false)
    private State state;

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

    public State getState()
    {
        return this.state;
    }

    public void setState(final State state)
    {
        this.state = state;
    }

}
