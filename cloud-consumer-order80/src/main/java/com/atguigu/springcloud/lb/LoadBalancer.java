package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    public ServiceInstance instances(List<ServiceInstance> instance);
}
