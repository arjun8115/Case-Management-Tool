package com.lawtendo.cmtool.application.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}


	private Predicate<String> postPaths() {
		return regex("/.*");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("CM Tool")
				.description("cmtool")
				.termsOfServiceUrl("http://javainuse.com").version("1.0").build();
	}
}
