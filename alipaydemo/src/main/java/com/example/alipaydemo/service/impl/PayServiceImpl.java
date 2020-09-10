package com.example.alipaydemo.service.impl;

import com.alipay.api.AlipayApiException;
import com.example.alipaydemo.bean.AlipayBean;
import com.example.alipaydemo.config.AlipayUtil;
import com.example.alipaydemo.service.PayService;
import org.springframework.stereotype.Service;

/* 支付服务 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}