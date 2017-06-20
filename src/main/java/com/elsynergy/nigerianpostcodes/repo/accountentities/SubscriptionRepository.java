package com.elsynergy.nigerianpostcodes.repo.accountentities;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Subscription;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface SubscriptionRepository extends CrudRepository<Subscription, Long>
{
    Optional<Subscription> findOneByAccountId(Long accountId);
}
