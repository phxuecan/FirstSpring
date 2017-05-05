package com.leehq.spring;

import com.leehq.spring.storage.StorageProperties;
import com.leehq.spring.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created by putao_lhq on 17-4-27.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties(StorageProperties.class)
public class Application
{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer()
    {
        return configurableEmbeddedServletContainer ->
        {
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html"));
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        };
    }

    @Bean
    CommandLineRunner init(StorageService storageService)
    {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
