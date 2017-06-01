package com.elsynergy.nigerianpostcodes.config;

import com.google.common.base.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource(value = "classpath:version.properties", ignoreResourceNotFound = true)
public class SwaggerConfig
{
    @Value("${version}")
    private String version;

    private Predicate<String> excludePaths()
    {
        return or(
                regex("/error"),
                regex("/health"),
                regex("/health.json"),
                regex("/shutdown"),
                regex("/shutdown.json"));
    }

    @Bean
    public Docket nigerianpostcodesApi()
    {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Nigerian Postcodes API")
                .contact(new Contact("El-Synergy Dev Team", "http://www.el-synergy.com", "dev@el-synergy.com"))
                .version(this.version)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(not(this.excludePaths()))
                    .build()
                    .apiInfo(apiInfo);

    }
}

