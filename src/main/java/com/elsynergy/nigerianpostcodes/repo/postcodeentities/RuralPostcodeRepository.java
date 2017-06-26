package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public interface RuralPostcodeRepository extends CrudRepository<RuralPostcode, Integer>
{
    public List<RuralPostcode> findByLocalGovernmentAreaStateCode(String stateCode);

    public List<RuralPostcode> findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(String stateCode, String localGovtAreaName);

    public List<RuralPostcode> findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrict(String stateCode, String localGovtAreaName, String district);

    public List<RuralPostcode> findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrictAndTown(String stateCode, String localGovtAreaName, String district, String town);
}
