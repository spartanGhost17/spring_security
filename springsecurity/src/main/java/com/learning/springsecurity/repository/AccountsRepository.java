package com.learning.springsecurity.repository;

import com.learning.springsecurity.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<AccountsRepository, Long> {

    Accounts findByCustomerId(int customerId);
}