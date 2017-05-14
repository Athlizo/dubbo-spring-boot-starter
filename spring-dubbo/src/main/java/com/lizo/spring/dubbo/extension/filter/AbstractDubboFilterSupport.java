package com.lizo.spring.dubbo.extension.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.lizo.spring.dubbo.extension.AbstractDubboExtensionWrapper;

/**
 * Created by lizhou on 2017/3/3/003.
 */

public abstract class AbstractDubboFilterSupport extends AbstractDubboExtensionWrapper<Filter> implements Filter {
    public AbstractDubboFilterSupport() {
        super(Filter.class);
    }
}
