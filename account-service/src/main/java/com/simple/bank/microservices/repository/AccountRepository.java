package com.simple.bank.microservices.repository;

import com.simple.bank.microservices.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByAccountNo(String accountNo);
    List<Account> findByCustomerNo(String customerNo);
}
