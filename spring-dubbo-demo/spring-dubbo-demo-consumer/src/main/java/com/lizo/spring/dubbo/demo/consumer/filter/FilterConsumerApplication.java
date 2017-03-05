package com.lizo.spring.dubbo.demo.consumer.filter;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.lizo.spring.dubbo.boot.annotation.EnableDubbo;
import com.lizo.spring.dubbo.demo.consumer.ConsumerAction;
import com.lizo.spring.dubbo.demo.consumer.simpleexample.ConsumerApplication;
import com.lizo.spring.dubbo.extension.filter.AbstractDubboConsumerFilterSupport;
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
@EnableDubbo(basePackages = "com.lizo.spring.dubbo.demo")
@ComponentScan(basePackages = "com.lizo.spring.dubbo.demo")
public class FilterConsumerApplication {

    @Bean
    ConsumerFilter consumerFilter() {
        return new ConsumerFilter();
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(ConsumerApplication.class)
                .web(false)
                .run(args);
        ctx.getBean(ConsumerAction.class).add(21, 25);
        new CountDownLatch(1).await();
    }

    static class ConsumerFilter extends AbstractDubboConsumerFilterSupport {
        public Result invoke(Invoker<?> invoker, Invocation invocation) {
            System.out.println("ConsumerFilter");
            return invoker.invoke(invocation);
        }
    }

}
