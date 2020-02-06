package com.kenyon.springboot2train.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 使用CORS解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 表示本应用的所有方法都会去处理跨域请求
        .allowedOrigins("http://localhost:8081") //表示允许8081访问当前8080的接口
        .allowedMethods("*") // allowedMethods 表示允许通过的请求数
        .allowedHeaders("*"); //allowedHeaders 则表示允许的请求头
    }
}