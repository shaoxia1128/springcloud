package com.yhx.springcloud.service.impl;

import com.yhx.springcloud.service.HystrixOrderServer;
import org.springframework.stereotype.Component;

@Component
public class HystrixOrderServerImpl  implements HystrixOrderServer {
    @Override
    public String paymentInfo_OK(Integer id) {
        return"线程池:"+Thread.currentThread().getName()+"接口实现方式-系统繁忙";

    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return"线程池:"+Thread.currentThread().getName()+"接口实现方式-系统繁忙";
    }
}
