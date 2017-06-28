package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
@Repository
public interface FacilityPostcodeRepositoryCustom
{
    public List<FacilityPostcode> getFacilityPostcodes(String stateCode,String localGovtAreaName, String facilityName);

}
