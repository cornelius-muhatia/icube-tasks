/*
 * Copyright (C) 2020 Cornelius M.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cmuhatia.icube.question.three.config;

import com.cmuhatia.icube.question.three.dto.ResponseWrapper;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cornelius M
 */
@Configuration
@EnableSwagger2
public class SwaggerUI {

    /**
     * Global response
     */
    private final List<ResponseMessage> globalResponse;
    /**
     * Application name
     */
    @Value("${app.name}")
    private String appName;
    /**
     * Application description
     */
    @Value("${app.description}")
    private String appDescription;
    /**
     * Application version
     */
    @Value("${app.version}")
    private String appVersion;
    /**
     * Swagger model resolver
     */
    private final TypeResolver typeResolver = new TypeResolver();

    /**
     * Default constructor. Initializes default global responses
     */
    public SwaggerUI() {
        globalResponse = List.of(
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad request due validation errors or invalid request")
                        .responseModel(new ModelRef("ResponseWrapper"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(500)
                        .message("Internal server error")
                        .responseModel(new ModelRef("ResponseWrapper"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("Unauthorized request")
                        .responseModel(new ModelRef("ResponseWrapper"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .message("Access denied")
                        .responseModel(new ModelRef("ResponseWrapper"))
                        .build()
        );
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/auditevents.*|/error|/autoconfig.*|/beans.*"
                        + "|/configprops.*|/dump.*|/features.*|/info.*|/mapping.*|/trace.*|/env.*|/pause.*"
                        + "|/refresh.*|/resume.*|/heapdump.*|/loggers.*|/restart.*|/oauth/error.*|/oauth/confirm_access.*|/actuator.*"
                        + "|/oauth/authorize.*|/oauth/token_key")))
                .build()
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET, globalResponse)
                .globalResponseMessage(RequestMethod.POST, globalResponse)
                .globalResponseMessage(RequestMethod.PUT, globalResponse)
                .globalResponseMessage(RequestMethod.DELETE, globalResponse)
                .useDefaultResponseMessages(false)
                .additionalModels(this.typeResolver.resolve(ResponseWrapper.class))
                ;
    }

    /**
     * Used to provide application information:
     * <ul>
     * <li>Application Name</li>
     * <li>Application Description</li>
     * <li>Application Version</li>
     * </ul>
     *
     * @return {@link ApiInfo}
     */
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appName)
                .description(appDescription)
                .version(appVersion)
                .contact(new Contact("Cornelius M.", null, "jdialogc@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

}
