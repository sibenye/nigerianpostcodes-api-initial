package com.elsynergy.nigerianpostcodes.repo.geographyentities;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface StateRepository extends CrudRepository<State, Integer>
{
    Optional<State> findOneByCode(String stateCode);
}
