package com.gft.newmagicplatform.repository;

import org.springframework.data.repository.CrudRepository;

import com.gft.newmagicplatform.entity.Account;

public interface AccountRepo extends CrudRepository<Account, Long> {

}
