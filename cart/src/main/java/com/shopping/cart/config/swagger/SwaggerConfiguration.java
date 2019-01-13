package com.shopping.cart.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Burak Naroglu
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.shopping.cart")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("ShoppingCart REST API", "Kategori bilgileri: Sistemde Telefon ve Aksesuar adinda iki tane kategori bulunmaktadir. " +
                "| Kupon bilgileri: 100 Tl uzeri alimlarda ekstra %10 indirim tanimli kuponumuz mevcuttur. " +
                "| Kampanya bilgileri: 3 adet kampanya tanimlidir. Telefon kategorisinde 3 adetten fazla urun aliminda ekstra %20 indirim mevcuttur. Aksesuar kategorisinde ise 5 adet uzeri alimlarda ekstra %50 ve ayrica 5 tl indirim mevcuttur. " +
                "| Urun bilgileri: Apple ve Telefon Kılıfı olmak uzere iki adet urun tanimlidir.", "1.0.0", "http://localhost:8080/",
                new Contact("Burak NAROGLU", "", "burak.naroglu@gmail.com"), "BN",
                "", Collections.emptyList());
    }

}