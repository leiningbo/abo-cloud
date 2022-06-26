package com.abo.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-06-26 16:21
 **/
@Configuration
public class RibbonRule {

    @Bean
    public IRule iRule() {
        return new WeightedResponseTimeRule();
    }

}
