package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public interface FacilityPostcodeRepository extends CrudRepository<FacilityPostcode, Integer>
{
    public List<FacilityPostcode> findByLocalGovernmentAreaStateCode(String stateCode);

    public List<FacilityPostcode> findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(String stateCode, String localGovtAreaName);

    public List<FacilityPostcode> findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndFacility(String stateCode, String localGovtAreaName, String facilityName);
}
