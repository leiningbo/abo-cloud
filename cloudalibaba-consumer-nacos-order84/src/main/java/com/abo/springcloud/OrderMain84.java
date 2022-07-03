package com.abo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-03 20:44
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class OrderMain84 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain84.class, args);
    }

}
