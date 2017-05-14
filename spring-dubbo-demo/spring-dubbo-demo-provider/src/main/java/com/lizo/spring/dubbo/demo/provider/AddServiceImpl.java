package com.lizo.spring.dubbo.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.lizo.spring.dubbo.demo.DemoApi;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by lizhou on 2017/2/28/028.
 */
@Service
public class AddServiceImpl implements DemoApi ,InitializingBean{

    public int add(int a, int b) {
        return a + b;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println(1);
    }
}
