package com.abo.springcloud.feignservice;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-26 16:41
 **/
@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/getById/{id}")
    Result getPaymentById(@PathVariable(value = "id") Long id);

}
