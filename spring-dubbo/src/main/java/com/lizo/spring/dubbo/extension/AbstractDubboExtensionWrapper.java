package com.lizo.spring.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.springframework.core.Ordered;

/**
 * Created by lizhou on 2017/3/3/003.
 */
public abstract class AbstractDubboExtensionWrapper<T> implements DubboExtensionWrapper<T>,Ordered{
    protected ExtensionLoader<T> extensionLoader;
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
