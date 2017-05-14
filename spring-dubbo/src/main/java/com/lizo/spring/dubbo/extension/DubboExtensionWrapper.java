package com.lizo.spring.dubbo.extension;

/**
 * Created by lizhou on 2017/3/3/003.
 */
public interface DubboExtensionWrapper<T> {

    Class getExtensionClass();

    //该返回对象必须有@Activate注解
    T getDefaultExtension();
}
