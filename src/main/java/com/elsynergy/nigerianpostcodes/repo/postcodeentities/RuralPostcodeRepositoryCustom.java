package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public interface RuralPostcodeRepositoryCustom
{
    public List<RuralPostcode> getRuralPostcodes(String stateCode,String localGovtAreaName, String district, String town);

}
