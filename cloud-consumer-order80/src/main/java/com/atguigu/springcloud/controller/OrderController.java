package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

@Controller
public class OrderController {

    //    public static final String PAYMENT_URL = "http://localhost:8001";    //单机测试时写死了
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";   //集群名字
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancer loadBalancer;


    @GetMapping("/consumer/payment/savePayment")
    @ResponseBody
    public CommonResult<Payment> savePayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/savePayment", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/findById/{id}")
    @ResponseBody
    public CommonResult<Payment> findById(@PathVariable("id") int id) {

        System.out.println("从8001服务器上获得的id" + id);

        return restTemplate.getForObject(PAYMENT_URL + "/payment/findById/" + id, CommonResult.class);

    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    @ResponseBody
    public CommonResult<Payment> getPayment2(@PathVariable("id") int id) {

        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/findById/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult<>(404,"失败");
        }

    }


    @GetMapping("/consumer/payment/lb")
    @ResponseBody
    public String getPaymnetLB(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if (instances == null || instances.size() <= 0 ){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
