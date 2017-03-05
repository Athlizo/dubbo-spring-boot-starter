package com.lizo.spring.dubbo.demo.provider.simpleexample;

import com.lizo.spring.dubbo.boot.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lizhou on 2017/2/28/028.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lizo.spring.dubbo.demo")
@EnableDubbo(basePackages = "com.lizo.spring.dubbo.demo")
public class ProviderApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(ProviderApplication.class)
                .web(false)
                .run(args);
        new CountDownLatch(1).await();
    }
}
