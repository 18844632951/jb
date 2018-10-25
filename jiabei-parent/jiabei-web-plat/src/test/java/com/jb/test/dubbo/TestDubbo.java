package com.jb.test.dubbo;

import com.alibaba.fastjson.JSON;
import com.jb.entity.ProductType;
import com.jb.facade.service.ProductTypeCenterService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 27654 on 2018/10/15.
 */
public class TestDubbo {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:dubbo-palt-consumer.xml");
        context.start();
        System.err.println("消费者启动..........");
        ProductTypeCenterService dub = (ProductTypeCenterService) context.getBean(ProductTypeCenterService.class);
        List<ProductType> type = dub.getTypes(0);
        System.err.println("============="+ JSON.toJSONString(type));
        System.in.read();
    }
}
