package com.abo.springcloud.feign;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.feign.impl.PaymentFallbackService;
import com.abo.springcloud.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-03 21:41
 **/
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface  PaymentService {

    @RequestMapping(value = "/paymentSQL/{id}")
    Result<Payment> paymentSQL(@PathVariable("id") Long id);

}
