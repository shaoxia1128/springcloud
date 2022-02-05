package com.yhx.springcloud.service;

import com.yhx.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService {

    Payment selectById(@Param("id")long id);
    int insertPayMent(Payment payment);
}
