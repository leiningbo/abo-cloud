package com.abo.springcloud.feign.impl;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.feign.PaymentService;
import com.abo.springcloud.response.Result;
import org.springframework.stereotype.Component;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-03 21:44
 **/
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public Result<Payment> paymentSQL(Long id) {
        return Result.suc("服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }


}
