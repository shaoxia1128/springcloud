package com.yhx.springcloud.coonfig;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level getFeignConfig(){
        return Logger.Level.FULL;
    }

}
