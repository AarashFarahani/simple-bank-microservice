package com.simple.bank.microservices.repository;

import com.simple.bank.microservices.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByCustomerNo(String customerNo);
}
