package com.yhx.springcloud.service;


import com.yhx.springcloud.entities.CommonMessage;
import com.yhx.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface OpenFeignService {

    @GetMapping("/payment/get/{id}")
    public CommonMessage<Payment> getPaymentById(@PathVariable("id") Long id);

}
