package com.yhx.springcloud.controller;

import com.yhx.springcloud.entities.CommonMessage;
import com.yhx.springcloud.entities.Payment;
import com.yhx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/payment/create")
    public CommonMessage creatPayment(@RequestBody Payment payment){
      int i=  paymentService.insertPayMent(payment);
      if(i>0){
         return  new CommonMessage("200","插入成功",i);
      }else{
          return  new CommonMessage("400","插入失败",i);
      }
    }
    @GetMapping("/payment/get/{id}")
    public CommonMessage<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment=  paymentService.selectById(id);
        if(ObjectUtils.isEmpty(payment)){
            return  new CommonMessage("400","暂无数据",payment);
        }else{
            return  new CommonMessage("200","查询成功"+serverPort,payment);
        }

    }

    @GetMapping("/payment/getDiscover")
    public Object getDiscover(){

      List<String> services = discoveryClient.getServices();
      for(String a :services){
          System.out.println(a);

      }
      List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
      for (ServiceInstance element :instances)
          System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"
                  +element.getUri());
      return this.discoveryClient ;
  }



}
