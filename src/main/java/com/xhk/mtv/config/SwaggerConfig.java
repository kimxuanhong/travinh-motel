package com.xhk.mtv.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public MVCConfig forwardToIndex() {
        return new MVCConfig() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/api").setViewName("forward:/swagger-ui.html");
            }
        };
    }

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(
                        newArrayList(new ParameterBuilder().name("Authorization").description("bearer {{access_token}}")
                                .modelRef(new ModelRef("string")).parameterType("header").required(false).build()))
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.xhk.mtv")).paths(apiPaths())
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
    }

    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("MTV API Documents").description("Documents with Swagger 2")
                .termsOfServiceUrl("http://localhost:8080").contact("").license("").licenseUrl("").version("1.0")
                .build();
    }

    private Predicate<String> apiPaths() {
        return Predicates.alwaysTrue();
    }
}
