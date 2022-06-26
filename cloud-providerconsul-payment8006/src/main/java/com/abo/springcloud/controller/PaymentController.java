package com.abo.springcloud.controller;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.response.Result;
import com.abo.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-26 14:11
 **/
@RestController
@Slf4j
@RequestMapping(value = "/consulPayment")
public class PaymentController {

    @Value(value = "${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam(defaultValue = "0") Integer pageIndex,
                       @RequestParam(defaultValue = "15") Integer pageSize) {
        Page<Payment> page = new Page<>(pageIndex, pageSize);
        log.info("serverPort:{}", serverPort);
        IPage<Payment> result = paymentService.page(page, null);
        return Result.suc(result);
    }


}
