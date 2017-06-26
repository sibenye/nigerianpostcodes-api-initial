package com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;

import javax.persistence.*;

/**
*
* @author silver.ibenye
*
*/
@Entity
@Table(name = "urban_postcodes")
public class UrbanPostcode
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "town")
    private String town;

    @Column(name = "street")
    private String street;

    @Column(name = "area")
    private String area;

    @Column(name = "postcode")
    private String postcode;

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

    public String getTown()
    {
        return this.town;
    }

    public void setTown(final String town)
    {
        this.town = town;
    }

    public String getStreet()
    {
        return this.street;
    }

    public void setStreet(final String street)
    {
        this.street = street;
    }

    public String getArea()
    {
        return this.area;
    }

    public void setArea(final String area)
    {
        this.area = area;
    }

    public String getPostcode()
    {
        return this.postcode;
    }

    public void setPostcode(final String postcode)
    {
        this.postcode = postcode;
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
