package com.kenyon.springboot2train.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kenyon.springboot2train.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("springboot利用swagger2构建api文档")
                        .description("springboot利用swagger2构建api文档，详细信息......")
                        .version("1.0")
                        .contact(new Contact("kenyon","https://github.com/kenyonlover/springboot2train","kenyon@gmail.com"))
                        //.license("The Apache License")
                        //.licenseUrl("http://www.kenyon.org")
                        .build());
    }
}