package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entities.Payment;

public interface IPaymentService {

    public int  savePayment(Payment payment);

    public Payment findById(int id);
}
