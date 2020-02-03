package com.kenyon.springboot2train.config;

import com.kenyon.springboot2train.condition.NoodlesCondition;
import com.kenyon.springboot2train.condition.RiceCondition;
import com.kenyon.springboot2train.itf.Food;
import com.kenyon.springboot2train.itf.impl.Noodles;
import com.kenyon.springboot2train.itf.impl.Rice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 这个配置类，大家重点注意两个地方：
 *
 * 两个 Bean 的名字都为 food，这不是巧合，而是有意取的。两个 Bean 的返回值都为其父类对象 Food。
 *
 * 每个 Bean 上都多了 @Conditional 注解，当 @Conditional 注解中配置的条件类的 matches 方法返回值为 true 时，对应的 Bean 就会生效。
 */
@Configuration
public class JavaConfig {
    @Bean("food")
    @Conditional(RiceCondition.class)
    Food rice() {
        return new Rice();
    }

    @Bean("food")
    @Conditional(NoodlesCondition.class)
    Food noodles() {
        return new Noodles();
    }
}