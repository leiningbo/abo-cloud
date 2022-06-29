package com.abo.springcloud.feignservice;

import com.abo.springcloud.response.Result;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackServoce implements PaymentFeignService {


    @Override
    public Result paymentOk(Integer id) {
        return Result.suc("-------------ok");
    }

    @Override
    public Result paymentTimeOut(Integer id) {
        return Result.suc("-------------超市------");
    }
}
