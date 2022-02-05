package com.yhx.springcloud.controller;

import com.yhx.springcloud.config.RestTemplateConfig;
import com.yhx.springcloud.entities.CommonMessage;
import com.yhx.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class OrderController {
    private final static String   url = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate  RestTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonMessage<Payment> create(Payment payment) {

        return RestTemplate.postForObject(url + "/payment/create", payment, CommonMessage.class);

    }
     @GetMapping("/consumer/payment/get/{id}")
    public  CommonMessage<Payment> selectOne(@PathVariable("id") long id){
         return RestTemplate.getForObject(url+"/payment/get/"+id,CommonMessage.class,id);
    }

}
