package com.abo.springcloud.controller;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.response.Result;
import com.abo.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-25 22:12
 **/
@RestController
@Slf4j
@RequestMapping(value = "/consumer/payment")
public class PaymentController {

    @Autowired
    private PaymentFeignService paymentFeignService;


    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public Result getById(@PathVariable(value = "id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }


}
