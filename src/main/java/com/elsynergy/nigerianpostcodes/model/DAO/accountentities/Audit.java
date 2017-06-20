package com.elsynergy.nigerianpostcodes.model.DAO.accountentities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Calendar;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Audit
{
    @CreatedDate
    @Column(name = "datecreated", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreated;

    @LastModifiedDate
    @Column(name = "datemodified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateModified;


    public Calendar getDateCreated()
    {
        return this.dateCreated;
    }

    public void setDateCreated(final Calendar dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateModified()
    {
        return this.dateModified;
    }

    public void setDateModified(final Calendar dateModified)
    {
        this.dateModified = dateModified;
    }

}
