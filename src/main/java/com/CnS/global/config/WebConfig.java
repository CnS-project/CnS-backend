package com.CnS.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowCredentials(true) // session 인증 설정
            .allowedHeaders("*")
            .exposedHeaders("*")
            .allowedOrigins("*");
//        registry.addMapping("/**")
//            .allowedOrigins("*")   // 추후 frontend 배포 ip 추가하기
//            .allowedHeaders("*")
//            .allowedMethods("*"); //get,post,patch 등 모든 허용할 HTTP method정의
    }
}