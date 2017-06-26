package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public interface PostcodeRepositoryCustom
{
    public List<FacilityPostcode> getFacilityPostcode(String stateCode,String localGovtAreaName, String facilityName);

    public List<RuralPostcode> getRuralPostcode(String stateCode,String localGovtAreaName, String district, String town);

    public List<UrbanPostcode> getUrbanPostcode(String stateCode,String town, String area, String street);

}
