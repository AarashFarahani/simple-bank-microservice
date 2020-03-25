package com.simple.bank.microservices.controller;

import com.simple.bank.microservices.model.AccountType;
import com.simple.bank.microservices.repository.AccountTypeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountType")
@Api("Every account has own type")
public class AccountTypeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private Environment environment;
    @Autowired private AccountTypeRepository accountTypeRepository;

    @ApiOperation("Get account type depends on code")
    @GetMapping("/{code}")
    public AccountType getAccountType(@PathVariable String code) {
        AccountType accountType = this.accountTypeRepository.findByCode(code).get();
        accountType.setPort(this.environment.getProperty("local.server.port"));

        logger.info("{}", accountType);

        return accountType;
    }
}
