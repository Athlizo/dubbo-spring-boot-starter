package com.lizo.spring.dubbo.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lizo.spring.dubbo.extension.context.ExtensionBeanPostProcessor ;

/**
 * Created by lizo on 2017/3/5
 */
@Configuration
@ConditionalOnClass({ ExtensionBeanPostProcessor.class})
public class ExtensionConfiguration {

    @Bean
    ExtensionBeanPostProcessor extensionBeanPostProcessor(){
        return new ExtensionBeanPostProcessor();
    }
}
