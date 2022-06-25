package com.abo.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-25 22:10
 **/
@Configuration
public class RestfulConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
