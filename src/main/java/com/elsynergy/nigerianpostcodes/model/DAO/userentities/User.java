package com.elsynergy.nigerianpostcodes.model.DAO.userentities;



import java.util.Date;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    private Role userRole;

    @ManyToOne
    private Package userPackage;

    @Column(name = "dateCreated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "dateModified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    public Long getId()
    {
        return this.id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPasswordHash()
    {
        return this.passwordHash;
    }

    public void setPasswordHash(final String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public Boolean getActive()
    {
        return this.active;
    }

    public void setActive(final Boolean active)
    {
        this.active = active;
    }

    public Role getUserRole()
    {
        return this.userRole;
    }

    public void setUserRole(final Role userRole)
    {
        this.userRole = userRole;
    }

    public Package getUserPackage()
    {
        return this.userPackage;
    }

    public void setUserPackage(final Package userPackage)
    {
        this.userPackage = userPackage;
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
