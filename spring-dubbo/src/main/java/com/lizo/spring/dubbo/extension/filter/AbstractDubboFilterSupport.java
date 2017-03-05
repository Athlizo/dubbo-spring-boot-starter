package com.lizo.spring.dubbo.extension.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.Holder;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.lizo.spring.dubbo.extension.AbstractDubboExtensionWrapper;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by lizhou on 2017/3/3/003.
 */

public abstract class AbstractDubboFilterSupport extends AbstractDubboExtensionWrapper<Filter> implements Filter {


    public AbstractDubboFilterSupport() {
        super(Filter.class);
    }



}
