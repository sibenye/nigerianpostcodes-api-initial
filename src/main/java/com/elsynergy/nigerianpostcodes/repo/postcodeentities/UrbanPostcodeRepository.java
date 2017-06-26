package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public interface UrbanPostcodeRepository extends CrudRepository<UrbanPostcode, Integer>
{
    public List<UrbanPostcode> findByStateCode(String stateCode);

    public List<UrbanPostcode> findByStateCodeAndTown(String stateCode, String town);

    public List<UrbanPostcode> findByStateCodeAndTownAndArea(String stateCode, String town, String area);

    public List<UrbanPostcode> findByStateCodeAndTownAndAreaAndStreet(String stateCode, String town, String area, String street);
}
