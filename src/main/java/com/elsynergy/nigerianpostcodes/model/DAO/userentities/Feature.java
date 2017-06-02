package com.elsynergy.nigerianpostcodes.model.DAO.userentities;

import java.sql.Date;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "features")
public class Feature
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "dateCreated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "dateModified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

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

}
