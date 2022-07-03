package com.abo.springcloud.controller;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.feign.PaymentService;
import com.abo.springcloud.response.Result;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-03 20:46
 **/
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    // OpenFeign
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public Result<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }



    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public Result fallback(@PathVariable Long id) {
        Result<Result> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id, Result.class,id);
        Map<String,Object> map = (Map<String,Object>) result.getData();

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (map.get("data") == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    //fallback
    public Result handlerFallback(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return Result.suc("兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

    //blockHandler
    public Result blockHandler(@PathVariable  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return Result.suc("blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }




}
