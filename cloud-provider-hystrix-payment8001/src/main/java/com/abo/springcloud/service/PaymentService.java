package com.abo.springcloud.service;

import com.abo.springcloud.entity.Payment;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PaymentService extends IService<Payment> {

    String paymentInfoOK(Integer id);

    String paymentInfoTimeOut(Integer id);

    String paymentCircuitBreaker(Integer id);

}
