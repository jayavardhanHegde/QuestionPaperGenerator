/*
 *
 */
package com.assignment.question.paper.generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author jayavardhan.hegde
 *
 */

@Configuration
public class AppConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("message");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    // @Bean
    // public MethodValidationPostProcessor methodValidationPostProcessor() {
    // return new MethodValidationPostProcessor();
    // }
}
