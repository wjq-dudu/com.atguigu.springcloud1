package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFengnService;

    @GetMapping("/consumer/payment/findById/{id}")
//    @ResponseBody
    public CommonResult findById(@PathVariable("id") int id){

        System.out.println("从接口找到controller");
        return paymentFengnService.findById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")   //openfeign  默认1秒钟拿到数据  超时报错
    public String PaymentFeignTimeout(){
        return paymentFengnService.PaymentFeignTimeout();
    }
}
