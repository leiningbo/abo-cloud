package com.abo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-02 20:23
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentNacosMain9001 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentNacosMain9001.class, args);

    }

}
