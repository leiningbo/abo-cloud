package com.abo.springcloud.feignservice;

import com.abo.springcloud.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-26 16:41
 **/
@Component
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = PaymentFallbackServoce.class)
public interface PaymentFeignService {

    @RequestMapping(value = "/payment/hystrix/ok/{id}", method = RequestMethod.GET)
    Result paymentOk(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/payment/hystrix/timeOut/{id}", method = RequestMethod.GET)
    Result paymentTimeOut(@PathVariable(value = "id") Integer id);



}
