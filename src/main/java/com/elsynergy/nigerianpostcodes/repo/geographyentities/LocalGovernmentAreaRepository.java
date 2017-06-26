package com.elsynergy.nigerianpostcodes.repo.geographyentities;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface LocalGovernmentAreaRepository extends CrudRepository<LocalGovernmentArea, Integer>
{
    Optional<LocalGovernmentArea> findOneByName(String localGovtAreaName);

    public List<LocalGovernmentArea> findByStateCode(String stateCode);

    public List<LocalGovernmentArea> findByStateCodeAndName(String stateCode, String localGovtAreaName);
}
