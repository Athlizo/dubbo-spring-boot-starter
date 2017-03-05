package com.lizo.spring.dubbo.demo.provider.filter;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.lizo.spring.dubbo.boot.annotation.EnableDubbo;
import com.lizo.spring.dubbo.demo.provider.simpleexample.ProviderApplication;
import com.lizo.spring.dubbo.extension.filter.AbstractDubboConsumerFilterSupport;
import com.lizo.spring.dubbo.extension.filter.AbstractDubboProviderFilterSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/3/5/005.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lizo.spring.dubbo.demo")
@EnableDubbo(basePackages = "com.lizo.spring.dubbo.demo")
public class FilterProviderApplication {

    @Bean
    ProviderFilter consumerFilter(){
        return new ProviderFilter();
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(ProviderApplication.class)
                .web(false) //把项目设置成非web环境
                .run(args);
        new CountDownLatch(1).await();
    }

    static class ProviderFilter extends AbstractDubboProviderFilterSupport {
        protected Result invoke(Invoker<?> invoker, Invocation invocation) {
            System.out.println("ProviderFilter");
            return invoker.invoke(invocation);
        }
    }
}
