package com.abo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-03 20:44
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMain84 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain84.class, args);
    }

}
