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

public abstract class AbstractDubboFilterSupport extends AbstractDubboExtensionWrapper<Filter> {


    public AbstractDubboFilterSupport() {
        super(Filter.class);
    }

    public AbstractDubboFilterSupport(String name) {
        super(Filter.class);
        super.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        if (name == null) {
            name = this.getClass().getName();
        }

        Holder holder = new Holder<Object>();
        Activate activate = this.getClass().getAnnotation(Activate.class);
        if (activate == null) {
            Filter filter = getDefaultFilter();
            holder.set(filter);
            activate = filter.getClass().getAnnotation(Activate.class);
            extensionLoader.addExtension(name, filter.getClass());
        } else {
            holder.set(this);
            extensionLoader.addExtension(name, this.getClass());
        }


        ConfigurablePropertyAccessor beanWrapper = PropertyAccessorFactory.forDirectFieldAccess(extensionLoader);
        Map<String, Activate> cachedActivates = (Map<String, Activate>) beanWrapper.getPropertyValue("cachedActivates");

        ConcurrentMap<String, Holder<Object>> cachedInstances = (ConcurrentMap<String, Holder<Object>>) beanWrapper.getPropertyValue("cachedInstances");


        cachedInstances.put(name, holder);
        cachedActivates.put(name, activate);
    }

    protected abstract Filter getDefaultFilter();

    protected abstract Result invoke(Invoker<?> invoker, Invocation invocation);


}
