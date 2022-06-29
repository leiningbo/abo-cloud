package com.abo.springcloud.service.impl;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.mapper.PaymentMapper;
import com.abo.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
