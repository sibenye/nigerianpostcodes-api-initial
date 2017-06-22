package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;

import javax.persistence.*;

/**
*
* @author silver.ibenye
*
*/
@Entity
@Table(name = "account_ip_access")
public class AccountIpAccess
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ipaddress", nullable = false)
    private String ipAddress;

    @ManyToOne(optional=false)
    @JoinColumn(name="accountid")
    private Account account;

    public AccountIpAccess()
    {

    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getIpAddress()
    {
        return this.ipAddress;
    }

    public void setIpAddress(final String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public Account getAccount()
    {
        return this.account;
    }

    public void setAccount(final Account account)
    {
        this.account = account;
    }

}
