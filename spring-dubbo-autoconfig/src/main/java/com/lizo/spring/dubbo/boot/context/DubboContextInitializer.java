package com.lizo.spring.dubbo.boot.context;

import com.lizo.spring.dubbo.boot.config.AnnotationBeanDefinitionRegistrar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by lizhou on 2017/2/25/025.
 */
public class DubboContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    public void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.addBeanFactoryPostProcessor(new DubboBeanDefinitionRegistryPostProcessor());
    }

    public class DubboBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
            RootBeanDefinition definition = new RootBeanDefinition(
                    AnnotationBeanDefinitionRegistrar.class);
            registry.registerBeanDefinition("annotationBeanDefinitionRegistrar", definition);
        }

        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        }
    }

}
