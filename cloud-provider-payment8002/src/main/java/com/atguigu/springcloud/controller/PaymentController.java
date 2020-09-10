package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/savePayment")
    @ResponseBody
    public CommonResult<Payment> savePayment(@RequestBody Payment payment) {
        int id = paymentService.savePayment(payment);

        System.out.println("***** 插入结果：" + id + payment);
        if (id > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort：" + serverPort, id);
        } else {
            return new CommonResult(404, "插入数据库失败,serverPort：" + serverPort, null);
        }

    }

    @GetMapping("/payment/findById/{id}")
    @ResponseBody
    public CommonResult findById(@PathVariable("id") int id) {
        Payment payment = paymentService.findById(id);

        System.out.println("***** 查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询数据库成功,serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(404, "没有查询到对应的记录：" + id + "serverPort：" + serverPort, null);
        }

    }

    @GetMapping("/payment/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String string : services) {
            System.out.println("string:" + string);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getInstanceId() + "-----" + instance.getHost() + "-----" + instance.getPort() + "-----" + instance.getUri());

        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String PaymentFeignTimeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


}
