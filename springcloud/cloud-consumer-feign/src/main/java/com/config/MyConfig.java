package com.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

public class MyConfig {


    @Bean
    public IRule ribbonRule() {
        return new com.netflix.loadbalancer.RandomRule();
    }
}
