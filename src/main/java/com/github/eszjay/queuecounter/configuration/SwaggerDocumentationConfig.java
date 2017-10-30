/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Queue Counter")
                .description("This API allows you to query the current progress of the queue processing system")
                .license("CC BY-SA")
                .licenseUrl("https://creativecommons.org/licenses/by-sa/4.0")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("", "", ""))
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.eszjay.queuecounter.api"))
                .build()
                .apiInfo(apiInfo());
    }

}
