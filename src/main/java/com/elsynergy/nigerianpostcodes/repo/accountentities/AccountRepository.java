package com.elsynergy.nigerianpostcodes.repo.accountentities;


import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.Account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
public interface AccountRepository extends CrudRepository<Account, Long>
{
    Optional<Account> findOneByAccountKey(String accountKey);

    Optional<Account> findOneByName(String accountName);
}
