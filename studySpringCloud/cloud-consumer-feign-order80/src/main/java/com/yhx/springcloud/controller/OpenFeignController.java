package com.yhx.springcloud.controller;


import com.yhx.springcloud.entities.CommonMessage;
import com.yhx.springcloud.entities.Payment;
import com.yhx.springcloud.service.OpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OpenFeignController {


    @Autowired
    private OpenFeignService  openFeignService;

    @GetMapping("/openFeign/payment/get/{id}")
    public CommonMessage<Payment> getOpenFeignOrder(@PathVariable("id")Long id){
         return  openFeignService.getPaymentById(id);
    }
}
