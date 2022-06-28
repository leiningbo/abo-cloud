package com.abo.springcloud.controller;

import com.abo.springcloud.feignservice.PaymentFeignService;
import com.abo.springcloud.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-25 22:12
 **/
@RestController
@Slf4j
@RequestMapping(value = "/consumer/payment")
public class OrderHystrixController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/hystrix/ok/{id}")
    public Result paymentOk(@PathVariable(value = "id") Integer id) {
        Result result = paymentFeignService.paymentOk(id);
        return result;
    }

    @RequestMapping(value = "/hystrix/timeOut/{id}")
    public Result paymentTimeOut(@PathVariable(value = "id") Integer id) {
        Result result = paymentFeignService.paymentTimeOut(id);
        return result;
    }



}
