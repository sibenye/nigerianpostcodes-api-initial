package com.elsynergy.nigerianpostcodes.model.DAO.userentities;

import com.elsynergy.nigerianpostcodes.model.enums.RoleEnum;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(final Integer id)
    {
        this.id = id;
    }

    public com.elsynergy.nigerianpostcodes.model.enums.RoleEnum getName()
    {
        return this.name;
    }

    public void setName(final com.elsynergy.nigerianpostcodes.model.enums.RoleEnum name)
    {
        this.name = name;
    }

}
