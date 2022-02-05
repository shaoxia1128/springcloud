package com.yhx.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yhx.springcloud.service.HystrixServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HistrixController {


    @Autowired
    private HystrixServer hystrixServer;


    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result =hystrixServer.paymentInfo_OK(id);
        log.info("****result: "+result);
        return result;
    }


    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)throws InterruptedException
    {
        String result =hystrixServer.paymentInfo_TimeOut(id);
        log.info("****result: "+result);
        return result;
    }


    @GetMapping("/payment/hystrix/cirCuitBreaker/{id}")
    public String paymentInfo_cirCuitBreaker(@PathVariable("id") Integer id)throws InterruptedException
    {
        String result =hystrixServer.cirCuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }


}
