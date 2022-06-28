package com.abo.springcloud.service.impl;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.mapper.PaymentMapper;
import com.abo.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public String paymentInfoTimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "xxx" + Thread.currentThread().getName() + "__ok,id:" + id + "\t" + "增加时长3秒";
    }

}
