package com.yhx.springcloud.service.impl;

import com.yhx.springcloud.dao.PaymentDao;
import com.yhx.springcloud.entities.Payment;
import com.yhx.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    private PaymentDao  paymentDao;


    @Override
    public Payment selectById(long id) {
        return paymentDao.selectById(id);
    }

    @Override
    public int insertPayMent(Payment payment) {
        return paymentDao.insertPayMent(payment);
    }
}
