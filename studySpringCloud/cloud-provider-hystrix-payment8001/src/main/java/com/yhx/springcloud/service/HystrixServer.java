package com.yhx.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.Name;
import java.util.concurrent.TimeUnit;

@Service
public class HystrixServer {
    /**
     *正常访问，一切OK
     *@paramid
     *@return
     */
    public String paymentInfo_OK(Integer id)
    {
        return"线程池:"+Thread.currentThread().getName()+"paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    /**
     *超时访问，演示降级
     *@paramid
     *@return
     */
    @HystrixCommand(fallbackMethod ="paymentInfo_TimeOutHandl",commandProperties = {
             @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
             })
    public String paymentInfo_TimeOut(Integer id)
    {
        try{ TimeUnit.SECONDS.sleep(5); }catch(InterruptedException e) { e.printStackTrace(); }
        return"线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O，耗费3秒";
    }
    public String paymentInfo_TimeOutHandl(Integer id){
        return"线程池:"+Thread.currentThread().getName()+"系统繁忙。请稍后再试"+id;

    }

    //服务熔断
    @HystrixCommand(fallbackMethod ="paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name ="circuitBreaker.enabled",value ="true"),
            @HystrixProperty(name =" circuitBreaker.requestVolumeThreshold",value ="10"),
            @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds",value ="10000"),
            @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage",value ="30"),
    })
    public String cirCuitBreaker(Integer id){
        if(id<0){
            throw  new RuntimeException("输入错误，不可以为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return  "流水号为："+serialNumber;

    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "我们是Hystrix熔断技术";
    }


}
