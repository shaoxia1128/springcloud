package com.yhx.springcloud.dao;

import com.yhx.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao
{
   Payment selectById(@Param("id")long id);
   int insertPayMent(Payment payment);

}