package com.simple.bank.microservices.proxy;

import com.simple.bank.microservices.proxy.model.Account;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="account-service")
public interface AccountServiceProxy {
    @GetMapping("/account-service/account/customer/{customerNo}")
    List<Account> findAccountByCustomer(@PathVariable String customerNo);
}
