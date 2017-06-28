package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public interface UrbanPostcodeRepositoryCustom
{
    public List<UrbanPostcode> getUrbanPostcodes(String stateCode,String town, String area, String street);

}
