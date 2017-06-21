package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;



import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;

import javax.persistence.*;

/**
 *
 * @author silver.ibenye
 *
 */
@Entity
@Table(name = "accounts")
public class Account extends Audit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "accountkey", nullable = false)
    private String accountKey;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(optional=false)
    @JoinColumn(name="roleid", nullable=false, updatable=false)
    private Role role;

    @ManyToOne(optional=true)
    @JoinColumn(name="packagetypeid")
    private PackageType packageType;

    @OneToOne(optional=true)
    @JoinColumn(name="subscriptionid")
    private AccountSubscription accountSubscription;

    public Account() {

    }

    public Account(final RegisterAccountRequest request) {
        this.name = request.getAccountName();
        this.setActive(true);
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(final Long id)
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

    public String getAccountKey()
    {
        return this.accountKey;
    }

    public void setAccountKey(final String accountKey)
    {
        this.accountKey = accountKey;
    }

    public Boolean getActive()
    {
        return this.active;
    }

    public void setActive(final Boolean active)
    {
        this.active = active;
    }

    public Role getRole()
    {
        return this.role;
    }

    public void setRole(final Role role)
    {
        this.role = role;
    }

    public PackageType getPackageType()
    {
        return this.packageType;
    }

    public void setPackageType(final PackageType packageType)
    {
        this.packageType = packageType;
    }

    public AccountSubscription getAccountSubscription()
    {
        return this.accountSubscription;
    }

    public void setAccountSubscription(final AccountSubscription accountSubscription)
    {
        this.accountSubscription = accountSubscription;
    }

}
