package com.simple.bank.microservices.repository;

import com.simple.bank.microservices.model.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
    Optional<AccountType> findByCode(String code);
}
