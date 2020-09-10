package com.atguigu.springcloud.service;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//value 很重要
public interface PaymentFeignService {

    @GetMapping("/payment/findById/{id}")
    public CommonResult<Payment> findById(@PathVariable("id") int id);

    @GetMapping("/payment/feign/timeout")
    public String PaymentFeignTimeout();
}
