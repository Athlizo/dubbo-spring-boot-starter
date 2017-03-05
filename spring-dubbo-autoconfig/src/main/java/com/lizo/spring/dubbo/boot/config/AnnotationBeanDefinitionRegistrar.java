package com.lizo.spring.dubbo.boot.config;


import com.lizo.spring.dubbo.boot.annotation.EnableDubbo;
import com.lizo.spring.dubbo.boot.context.AnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lizo on 2017/2/25
 */
@Configuration
public class AnnotationBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    private String BEAN_NAME = "annotationBeanPostProcessor";

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        List<String> basePackages = getPackagesToScan(importingClassMetadata);
        if (!registry.containsBeanDefinition(BEAN_NAME)) {
            addPostProcessor(registry, basePackages);
        }
    }

    private void addPostProcessor(BeanDefinitionRegistry registry, List<String> basePackages) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(AnnotationBeanPostProcessor.class);
        beanDefinition.getConstructorArgumentValues()
                .addGenericArgumentValue(basePackages);
        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(BEAN_NAME, beanDefinition);
    }


    private List<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(EnableDubbo.class.getName()));
        String[] basePackages = attributes.getStringArray("basePackages");
        return Arrays.asList(basePackages);
    }
}
