package com.abo.springcloud.controller;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.response.Result;
import com.abo.springcloud.service.PaymentService;
import com.auth0.jwt.impl.PublicClaims;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-25 20:39
 **/
@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {

    @Value(value = "${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam(defaultValue = "0") Integer pageIndex,
                       @RequestParam(defaultValue = "15") Integer pageSize) {
        Page<Payment> page = new Page<>(pageIndex, pageSize);
        log.info("serverPort:{}", serverPort);
        IPage<Payment> result = paymentService.page(page, null);
        return Result.suc(result);
    }

    @RequestMapping(value = "/create")
    public Result create(@RequestParam String no){
        Payment payment = new Payment();
        payment.setSerial(no);
        return Result.suc(paymentService.save(payment));
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public Result getById(@PathVariable("id") Long id) {
        log.info("serverPort:{}", serverPort);
        Payment payment = paymentService.getById(id);
        return Result.suc(payment);
    }

    @RequestMapping(value = "/lb", method = RequestMethod.GET)
    public Result lb() {
        log.info("serverPort:{}", serverPort);
        return Result.suc(serverPort);
    }

    @RequestMapping(value = "/discovery")
    public Result discovery(){
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            log.info("ssssss-----"+s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" +
                    instance.getPort() + "\t" + instance.getUri());
        }
        return Result.suc(this.discoveryClient);
    }

}
