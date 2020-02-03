package com.kenyon.springboot2train.config;

import com.kenyon.springboot2train.itf.Food;
import com.kenyon.springboot2train.itf.impl.Noodles;
import com.kenyon.springboot2train.itf.impl.Rice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class JavaConfig2 {
    @Bean("food")
    @Profile("南方人")
    Food rice() {
        return new Rice();
    }
    @Bean("food")
    @Profile("北方人")
    Food noodles() {
        return new Noodles();
    }
}