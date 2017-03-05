package com.lizo.spring.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * Created by lizhou on 2017/3/3/003.
 */
public interface DubboExtensionWrapper<T> {

    Class getExtensionClass();

    T getDefaultExtension();
}
