package com.simple.bank.microservices.controller;

import com.simple.bank.microservices.model.Customer;
import com.simple.bank.microservices.proxy.AccountServiceProxy;
import com.simple.bank.microservices.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Api("Customer Api")
public class CustomerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private CustomerRepository customerRepository;
    @Autowired private AccountServiceProxy accountServiceProxy;

    @ApiOperation(value = "Get customer detail", response = Customer.class)
    @GetMapping("/{customerNo}")
    public Customer getCustomer(@ApiParam(value = "Customer No", required = true)
                              @PathVariable String customerNo) {
        Customer customer = this.customerRepository.findByCustomerNo(customerNo).get();
        customer.setAccounts(this.accountServiceProxy.findAccountByCustomer(customerNo));

        logger.info("{}", customer);

        return customer;
    }
}
