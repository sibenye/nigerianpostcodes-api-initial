package com.elsynergy.nigerianpostcodes.model.DAO.userentities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private com.elsynergy.nigerianpostcodes.model.enums.Role name;

    public Long getId()
    {
        return this.id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public com.elsynergy.nigerianpostcodes.model.enums.Role getName()
    {
        return this.name;
    }

    public void setName(final com.elsynergy.nigerianpostcodes.model.enums.Role name)
    {
        this.name = name;
    }

}
