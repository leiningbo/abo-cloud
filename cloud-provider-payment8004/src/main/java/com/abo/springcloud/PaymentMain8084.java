package com.abo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-26 14:10
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8084 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8084.class, args);
    }

}
