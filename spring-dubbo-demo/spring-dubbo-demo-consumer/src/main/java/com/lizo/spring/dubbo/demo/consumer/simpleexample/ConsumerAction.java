package com.lizo.spring.dubbo.demo.consumer.simpleexample;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lizo.spring.dubbo.demo.DemoApi;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/2/28/028.
 */
@Component
public class ConsumerAction {

    @Reference
    private DemoApi demoApi;

    public void add(int a,int b){
        System.out.println("ret = " + demoApi.add(a,b));
    }

}
