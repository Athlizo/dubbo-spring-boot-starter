package com.lizo.spring.dubbo.extension.context;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.Holder;
import com.lizo.spring.dubbo.extension.DubboExtensionWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by lizo on 2017/3/5
 */
public class ExtensionBeanPostProcessor implements BeanPostProcessor,PriorityOrdered{
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof DubboExtensionWrapper){
            DubboExtensionWrapper dubboExtensionWrapper = (DubboExtensionWrapper) bean;
            Class c = dubboExtensionWrapper.getExtensionClass();
            ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(c);

            Holder holder = new Holder<Object>();
            Activate activate = bean.getClass().getAnnotation(Activate.class);
            if (activate == null) {
                Object extension = dubboExtensionWrapper.getDefaultExtension();
                holder.set(extension);
                activate = extension.getClass().getAnnotation(Activate.class);
                extensionLoader.addExtension(beanName, extension.getClass());
            } else {
                holder.set(this);
                extensionLoader.addExtension(beanName, bean.getClass());
            }


            ConfigurablePropertyAccessor beanWrapper = PropertyAccessorFactory.forDirectFieldAccess(extensionLoader);
            Map<String, Activate> cachedActivates = (Map<String, Activate>) beanWrapper.getPropertyValue("cachedActivates");

            ConcurrentMap<String, Holder<Object>> cachedInstances = (ConcurrentMap<String, Holder<Object>>) beanWrapper.getPropertyValue("cachedInstances");


            cachedInstances.put(beanName, holder);
            cachedActivates.put(beanName, activate);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
