package com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;

import javax.persistence.*;

/**
*
* @author silver.ibenye
*
*/
@Entity
@Table(name = "facility_postcodes")
public class FacilityPostcode
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "facility")
    private String facility;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @ManyToOne(optional=false)
    @JoinColumn(name="lgaid", nullable=false)
    private LocalGovernmentArea localGovernmentArea;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(final Integer id)
    {
        this.id = id;
    }

    public String getFacility()
    {
        return this.facility;
    }

    public void setFacility(final String facility)
    {
        this.facility = facility;
    }

    public String getPostcode()
    {
        return this.postcode;
    }

    public void setPostcode(final String postcode)
    {
        this.postcode = postcode;
    }

    public String getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(final String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(final String longitude)
    {
        this.longitude = longitude;
    }

    public LocalGovernmentArea getLocalGovernmentArea()
    {
        return this.localGovernmentArea;
    }

    public void setLocalGovernmentArea(final LocalGovernmentArea localGovernmentArea)
    {
        this.localGovernmentArea = localGovernmentArea;
    }

}
