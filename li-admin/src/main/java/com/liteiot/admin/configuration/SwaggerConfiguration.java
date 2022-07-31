//package com.liteiot.admin.configuration;
// TODO update api.doc
///**
// * Class:  SwaggerConfiguration
// * <p>
// * Author: zhaoyg
// * Date:   2021/9/30 17:41
// * Desc:   SwaggerConfiguration
// */
//
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.List;
//
//import static java.util.Collections.singletonList;
//
///**
// * 全局token
// */
//
////@Configuration
//public class SwaggerConfiguration {
//
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.regex("(?!/error.*).*"))
//                .build()
//                .securityContexts(singletonList(securityContext()))
//                // ApiKey的name需与SecurityReference的reference保持一致
//                .securitySchemes(singletonList(new ApiKey("token", "token", "")));
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                //.forPaths(PathSelectors.regex("/*.*"))
//                .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return singletonList(
//                new SecurityReference("token", authorizationScopes));
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Swagger API")
//                .description("this is a description")
//                .termsOfServiceUrl("http://springfox.io")
//                .contact(new Contact("springfox", "https://morris131.github.io", "morris131@163.com"))
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
//                .version("3.0")
//                .build();
//    }
//
//}
//
