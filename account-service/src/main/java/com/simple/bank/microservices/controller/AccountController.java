package com.simple.bank.microservices.controller;

import com.simple.bank.microservices.model.Account;
import com.simple.bank.microservices.proxy.AccountTypeServiceProxy;
import com.simple.bank.microservices.repository.AccountRepository;
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

import java.util.List;

@RestController
@RequestMapping("/account")
@Api("Account detail")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private AccountRepository accountRepository;
    @Autowired private AccountTypeServiceProxy accountTypeServiceProxy;

    @ApiOperation(value = "Get account detail", response = Account.class)
    @GetMapping("/{accountNo}")
    public Account getAccount(@ApiParam(value = "Account No", required = true)
                                      @PathVariable String accountNo) {
        Account account = this.accountRepository.findByAccountNo(accountNo).get();
        account.setDescription(this.accountTypeServiceProxy.getAccountType(account.getAccountTypeCode()).getDescription());

        logger.info("{}", account);

        return account;
    }

    @ApiOperation(value = "Get account list by customer No", response = List.class)
    @GetMapping("/customer/{customerNo}")
    public List<Account> findAccountByCustomer(@ApiParam(value = "Customer No", required = true)
                                      @PathVariable String customerNo) {
        List<Account> accountList = this.accountRepository.findByCustomerNo(customerNo);
        accountList.stream()
                .forEach(a-> a.setDescription(this.accountTypeServiceProxy
                        .getAccountType(a.getAccountTypeCode()).getDescription()));

        logger.info("{}", accountList);

        return accountList;
    }
}
