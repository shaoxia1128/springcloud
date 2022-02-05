package com.yhx.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class OrderController {
    private final static String   url = "http://consul-provider-payment";

    @Autowired
    private RestTemplate  RestTemplate;


    @GetMapping("consul/payment/consul")
    public  String  selectOne(){
         return RestTemplate.getForObject(url+"/payment/consul",String.class);
    }

}
