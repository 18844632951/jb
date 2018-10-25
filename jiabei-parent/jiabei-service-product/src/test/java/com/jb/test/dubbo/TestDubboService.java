package com.jb.test.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Dubbo 服务 启动 / 关闭
 * Created by 27654 on 2018/10/15.
 */
public class TestDubboService {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:spring-service-product.xml");
        ac.start();
        System.err.println("Dubbo 服务已启动... 状态 : 【正常】 按任意键关闭服务");
        System.in.read();
    }
}
