package com.CnS.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {  // Swagger

    private static final String API_NAME = "CnS API";
    private static final String API_VERSION = "1";
    private static final String API_DESCRIPTION = "CnS API 명세서";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage(
                "com.CnS.domain")) //API문서를 사용할 범위 지정
            .paths(PathSelectors.ant("/**")) //API의 url경로 지정
            .build()
            .apiInfo(apiInfo());

    }


    public ApiInfo apiInfo() {  // API의 이름, 현재 버전, API에 대한 정보
        return new ApiInfoBuilder()
            .title(API_NAME)
            .version(API_VERSION)
            .description(API_DESCRIPTION)
            .build();
    }

}