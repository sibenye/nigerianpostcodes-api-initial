package com.elsynergy.nigerianpostcodes.repo.userentities;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author silver.ibenye
 *
 */
public interface UserRepository extends CrudRepository<User, Long>
{

}
