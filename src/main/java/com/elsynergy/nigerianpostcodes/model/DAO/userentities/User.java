package com.elsynergy.nigerianpostcodes.model.DAO.userentities;



import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "users")
public class User extends Audit
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

    @Column(name = "roleid")
    private Integer roleId;

    @Column(name = "packageid")
    private Integer packageId;

    public User() {

    }

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

    public Integer getRoleId()
    {
        return this.roleId;
    }

    public void setRoleId(final Integer userRole)
    {
        this.roleId = userRole;
    }

    public Integer getPackageId()
    {
        return this.packageId;
    }

    public void setPackageId(final Integer userPackage)
    {
        this.packageId = userPackage;
    }

}
