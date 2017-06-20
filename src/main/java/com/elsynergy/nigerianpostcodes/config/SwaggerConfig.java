package com.elsynergy.nigerianpostcodes.config;

import com.google.common.base.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource(value = {
        "classpath:version.properties",
        "classpath:application.properties"
        }, ignoreResourceNotFound = true)
public class SwaggerConfig
{
    @Value("${version}")
    private String version;

    @Value("${app.prop.environment}")
    private String environment;

    @Value("${app.prop.swaggerAcctKey}")
    private String swaggerAcctKey;

    @Value("${app.prop.appName}")
    private String appName;

    @Value("${app.prop.apiKeyName}")
    private String apiKeyName;

    @SuppressWarnings("unchecked")
    private Predicate<String> excludePaths()
    {
        if (this.environment.equalsIgnoreCase("local")) {
            return or(
                    regex("/error"),
                    regex("/health.json"),
                    regex("/shutdown.json"));
        } else {
            return or(
                    regex("/error"),
                    regex("/health"),
                    regex("/health.json"),
                    regex("/shutdown"),
                    regex("/shutdown.json"),
                    regex("/admin/.*"));
        }

    }

    @Bean
    public Docket nigerianpostcodesApi()
    {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title(this.appName)
                .contact(new Contact("El-Synergy Dev Team", "http://www.el-synergy.com", "dev@el-synergy.com"))
                .version(this.version)
                .build();

        final ApiKey apiKey = new ApiKey("mykey", this.apiKeyName, "header");

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(not(this.excludePaths()))
                    .build()
                    .apiInfo(apiInfo)
                    .securitySchemes(Arrays.asList(apiKey));

    }

    @Bean
    public SecurityConfiguration security() {
      return new SecurityConfiguration(
          "", /*client id*/
          "", /*client secret*/
          "", /*realm*/
          this.appName,
          this.swaggerAcctKey,
          ApiKeyVehicle.HEADER,
          this.apiKeyName,
          "," /*scope separator*/);
    }
}

