package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.IPaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    IPaymentDao paymentDao;
    @Override
    public int savePayment(Payment payment) {
        return paymentDao.savePayment(payment);
    }

    @Override
    public Payment findById(int id) {
        return paymentDao.findById(id);
    }
}
