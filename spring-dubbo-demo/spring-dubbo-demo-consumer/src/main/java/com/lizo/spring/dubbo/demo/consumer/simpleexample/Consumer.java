package com.lizo.spring.dubbo.demo.consumer.simpleexample;

import com.lizo.spring.dubbo.boot.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lizhou on 2017/2/28/028.
 */
@SpringBootApplication
@EnableDubbo(basePackages = "com.lizo.spring.dubbo.demo.consumer")
public class Consumer {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(Consumer.class)
                .web(false) //把项目设置成非web环境
                .run(args);
        ctx.getBean(ConsumerAction.class).add(21,25);
        new CountDownLatch(1).await();
    }
}
