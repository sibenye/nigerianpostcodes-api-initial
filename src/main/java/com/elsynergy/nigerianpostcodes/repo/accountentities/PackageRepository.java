package com.elsynergy.nigerianpostcodes.repo.accountentities;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.PackageType;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface PackageRepository extends CrudRepository<PackageType, Integer>
{
    Optional<PackageType> findOneByName(String name);
}
