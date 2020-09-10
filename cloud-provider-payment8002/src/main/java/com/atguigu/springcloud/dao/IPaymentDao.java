package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IPaymentDao {

    public int  savePayment(Payment payment);

    public Payment findById(@Param("id") int id);
}
