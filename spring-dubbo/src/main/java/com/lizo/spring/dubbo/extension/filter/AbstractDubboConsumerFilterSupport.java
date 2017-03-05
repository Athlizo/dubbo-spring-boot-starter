package com.lizo.spring.dubbo.extension.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Created by lizhou on 2017/3/3/003.
 */
public abstract class AbstractDubboConsumerFilterSupport extends AbstractDubboFilterSupport {

    public Filter getDefaultExtension() {
        return new ConsumerActiveFilter(this);
    }


    @Activate(group = Constants.CONSUMER)
    static class ConsumerActiveFilter extends AbstractActiveFilterWrapper {

        public ConsumerActiveFilter(AbstractDubboFilterSupport abstractDubboFilterSupport) {
            super(abstractDubboFilterSupport);
        }
    }
}
