package com.restaurant.restaurant.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


@Configuration
public class MessageConfig {
    @Value("${spring.messages.basename}")
    private String baseName;

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(baseName);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }





}
