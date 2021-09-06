package com.lxt.learnsource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssemblingConfiguration {

    @Bean
    public AssemblingBean getAssemblingBean() {
        return new AssemblingBean();
    }

}
