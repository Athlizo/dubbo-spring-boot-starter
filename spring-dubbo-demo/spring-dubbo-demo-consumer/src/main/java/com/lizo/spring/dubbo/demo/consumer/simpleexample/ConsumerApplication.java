package com.lizo.spring.dubbo.demo.consumer.simpleexample;

import com.lizo.spring.dubbo.boot.annotation.EnableDubbo;
import com.lizo.spring.dubbo.demo.consumer.ConsumerAction;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lizhou on 2017/2/28/028.
 */
@SpringBootApplication
@EnableDubbo(basePackages = "com.lizo.spring.dubbo.demo")
@ComponentScan(basePackages = "com.lizo.spring.dubbo.demo")
public class ConsumerApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(ConsumerApplication.class)
                .web(false)
                .run(args);
        ctx.getBean(ConsumerAction.class).add(21,25);
        new CountDownLatch(1).await();
    }
}
