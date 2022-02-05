package com.yhx.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yhx.springcloud.service.HystrixOrderServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_TimeOutDefult")
public class HystrixOrderController {

    @Autowired
    private HystrixOrderServer hystrixOrderServer;



    @GetMapping("/hystrix/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result =hystrixOrderServer.paymentInfo_OK(id);
        log.info("****result: "+result);
        return result;
    }

    @GetMapping("/hystrix/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod ="paymentInfo_TimeOutHandl",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)throws InterruptedException
    {
        String result =hystrixOrderServer.paymentInfo_TimeOut(id);
        log.info("****result: "+result);
        return result;
    }

    public String paymentInfo_TimeOutHandl(Integer id){
        return"线程池:"+Thread.currentThread().getName()+"单个配置-系统繁忙。请稍后再试"+id;

    }
    public String paymentInfo_TimeOutDefult(Integer id){
        return"线程池:"+Thread.currentThread().getName()+"全局配置-系统繁忙。请稍后再试";
    }



}
