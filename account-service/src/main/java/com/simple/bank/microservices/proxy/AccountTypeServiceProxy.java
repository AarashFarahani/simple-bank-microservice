package com.simple.bank.microservices.proxy;


import com.simple.bank.microservices.proxy.model.AccountType;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="account-type-service")
@RibbonClient(name="account-type-service")
public interface AccountTypeServiceProxy {
    @GetMapping("/accountType/{code}")
    AccountType getAccountType(@PathVariable String code);
}
