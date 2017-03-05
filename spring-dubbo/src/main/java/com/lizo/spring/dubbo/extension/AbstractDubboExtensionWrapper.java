package com.lizo.spring.dubbo.extension;

import org.springframework.core.Ordered;

/**
 * Created by lizo on 2017/3/3
 */
public abstract class AbstractDubboExtensionWrapper<T> implements DubboExtensionWrapper<T>,Ordered{
    private Class<T> extensionClass;

    public AbstractDubboExtensionWrapper(Class<T> extensionClass) {
        this.extensionClass = extensionClass;
    }

    public Class<T> getExtensionClass() {
        return extensionClass;
    }

    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
