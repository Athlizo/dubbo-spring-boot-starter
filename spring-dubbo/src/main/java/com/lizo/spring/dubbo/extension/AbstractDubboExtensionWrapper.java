package com.lizo.spring.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by lizhou on 2017/3/3/003.
 */
public abstract class AbstractDubboExtensionWrapper<T> implements DubboExtensionWrapper<T>, InitializingBean,BeanFactoryAware {
    protected String name;

    protected BeanFactory beanFactory;

    protected ExtensionLoader<T> extensionLoader;
    private Class<T> extensionClass;

    public AbstractDubboExtensionWrapper(Class<T> extensionClass) {
        this.extensionClass = extensionClass;
    }

    public void afterPropertiesSet() throws Exception {
        extensionLoader = ExtensionLoader.getExtensionLoader(extensionClass);
    }


    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
