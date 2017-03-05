package com.lizo.spring.dubbo.boot.config;

import com.lizo.spring.dubbo.extension.context.ExtensionBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lizo on 2017/3/5
 */
@Configuration
@ConditionalOnClass({ExtensionBeanPostProcessor.class})
public class ExtensionConfiguration {

    @Bean
    ExtensionBeanPostProcessor extensionBeanPostProcessor() {
        return new ExtensionBeanPostProcessor();
    }
}
