package com.elsynergy.nigerianpostcodes.repo.userentities;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Package;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface PackageRepository extends CrudRepository<Package, Integer>
{
    Optional<Package> findOneByName(String name);
}
