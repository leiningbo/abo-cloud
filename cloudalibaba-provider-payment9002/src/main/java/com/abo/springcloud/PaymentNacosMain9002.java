package com.abo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-02 20:28
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentNacosMain9002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentNacosMain9002.class, args);

    }

}
