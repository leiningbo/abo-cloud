package com.abo.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: abo-cloud
 * @description:  添加@RefreshScope   Spring Cloud 的原生注解以启用配置更新的自动刷新
 * @author: leiningbo
 * @create: 2022-07-02 21:02
 **/
@RestController
@RefreshScope
@RequestMapping(value = "/config")
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public String getConfigInfo() {
        return configInfo;
    }


}
