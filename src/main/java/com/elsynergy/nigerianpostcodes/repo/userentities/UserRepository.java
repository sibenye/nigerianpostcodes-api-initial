package com.elsynergy.nigerianpostcodes.repo.userentities;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface UserRepository extends CrudRepository<User, Long>
{
    Optional<User> findOneByUsername(String username);
}
