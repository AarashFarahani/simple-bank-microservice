package com.simple.bank.microservices.proxy;


import com.simple.bank.microservices.proxy.model.AccountType;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="account-type-service")
public interface AccountTypeServiceProxy {
    @GetMapping("/account-type-service/accountType/{code}")
    AccountType getAccountType(@PathVariable String code);
}
