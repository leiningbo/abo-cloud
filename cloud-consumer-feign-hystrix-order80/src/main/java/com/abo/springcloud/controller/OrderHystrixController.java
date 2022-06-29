package com.abo.springcloud.controller;

import com.abo.springcloud.feignservice.PaymentFeignService;
import com.abo.springcloud.response.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "payment_default_fallback")
@RequestMapping(value = "/consumer/payment")
public class OrderHystrixController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/hystrix/ok/{id}")
    public Result paymentOk(@PathVariable(value = "id") Integer id) {

        Result result = paymentFeignService.paymentOk(id);
        return result;
    }

    @HystrixCommand(
//            fallbackMethod = "paymentInfoTimeOutHandler",commandProperties ={
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")}
            )
    @RequestMapping(value = "/hystrix/timeOut/{id}")
    public Result paymentTimeOut(@PathVariable(value = "id") Integer id) {
        int a = 10 / 0;
        Result result = paymentFeignService.paymentTimeOut(id);
        return result;
    }

    public Result paymentInfoTimeOutHandler(@PathVariable(value = "id") Integer id) {
        return Result.suc("我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)");
    }

    public Result payment_default_fallback() {
        return Result.suc("全局默认降级");
    }


}
