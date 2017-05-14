package com.lizo.spring.dubbo.demo.provider.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.lizo.spring.dubbo.boot.annotation.EnableDubbo;
import com.lizo.spring.dubbo.demo.provider.simpleexample.ProviderApplication;
import com.lizo.spring.dubbo.extension.filter.AbstractDubboFilterSupport;
import com.lizo.spring.dubbo.extension.filter.AbstractDubboProviderFilterSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/3/5/005.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lizo.spring.dubbo.demo")
@EnableDubbo(basePackages = "com.lizo.spring.dubbo.demo")
@Import(value = ProviderApplication.class)
public class FilterProviderApplication {


    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(ProviderApplication.class)
                .web(false)
                .run(args);
        new CountDownLatch(1).await();
    }

    @Bean
    ProviderFilter providerFilter() {
        return new ProviderFilter();
    }

    static class ProviderFilter extends AbstractDubboProviderFilterSupport {
        public Result invoke(Invoker<?> invoker, Invocation invocation) {
            System.out.println("ProviderFilter");
            return invoker.invoke(invocation);
        }
    }


    @Bean
    CustomFilter customFilter() {
        return new CustomFilter();
    }

    @Activate(group = Constants.PROVIDER)
    static class CustomFilter extends AbstractDubboFilterSupport {
        public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
            System.out.println("ProviderFilter2");
            return invoker.invoke(invocation);
        }

        public Filter getDefaultExtension() {
            return this;
        }
    }
}
