package com.abo.springcloud.controller;

import com.abo.springcloud.entity.Payment;
import com.abo.springcloud.handler.CustomerBlockHandler;
import com.abo.springcloud.response.Result;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-03 15:21
 **/
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        try {
            int a = 10/0;
        } catch (Exception e) {
            Tracer.trace(e);
        }
        return "------testB";
    }

    @RequestMapping(value = "/hotKey")
    @SentinelResource(value = "hotKey",blockHandler = "deal_hotkey")
    public Result testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {

        return Result.suc("testHotKey");
    }
    public Result deal_hotkey(String p1, String p2, BlockException e) {

        return Result.suc("拖后保护hotkey");
    }


    @RequestMapping(value = "/byResource")
    @SentinelResource(value = "byResource",blockHandler = "deal_resource")
    public Result byResource() {

        return Result.suc("test Resource");
    }
    public Result deal_resource(BlockException e) {

        return Result.fail(-1, e.getClass().getCanonicalName() + "拖后保护resource");
    }


    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public Result byUrl() {
        return Result.suc("按url限流测试OK",new Payment(2020L,"serial002"));
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException")
    public Result customerBlockHandler() {
        return Result.suc("按客戶自定义", new Payment(2020L, "serial003"));
    }





}
