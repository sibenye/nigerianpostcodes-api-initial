package com.elsynergy.nigerianpostcodes.repo.accountentities;

import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.AccountSubscription;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author silver.ibenye
 *
 */
public interface SubscriptionRepository extends CrudRepository<AccountSubscription, Long>
{
}
