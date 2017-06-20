package com.elsynergy.nigerianpostcodes.repo.accountentities;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Role;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface RoleRepository extends CrudRepository<Role, Integer>
{
    Optional<Role> findOneByName(String name);
}
