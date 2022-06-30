package com.abo.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.mapper.PaymentMapper;
import com.abo.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-25 20:36
 **/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Override
    public String paymentInfoOK(Integer id) {
        return "xxx" + Thread.currentThread().getName() + "__ok,id:" + id + "\t";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties ={
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")} )
    @Override
    public String paymentInfoTimeOut(Integer id) {
        int time = 5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "xxx" + Thread.currentThread().getName() + "paymentInfoTimeOut__ok,id:" + id + "\t" + "耗时秒：" + time;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "系统繁忙paymentInfo_TimeOutHandler,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    //==========服务熔断
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }




}
