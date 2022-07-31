package com.liteiot.admin.testEntity;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * CGLIB是一个强大的、高性能的代码生成库。其被广泛应用于AOP框架（Spring、dynaop）中，用以提供方法拦截操作。
 * </pre>
 * Author: 王俊超
 * Date: 2018-02-22 09:26
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
@Slf4j
public class ClassGeneration {
    public static void main(String[] args) throws ClassNotFoundException {

        // 设置类成员属性
        Map<String, Class<?>> propertyMap = new HashMap<>();

        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        propertyMap.put("xyz", Class.forName("java.lang.Double"));

        Demo demo = new Demo();
        demo.setId(3);
        demo.setName("张三");
        demo.setAddress("粤海街道");
        demo.setSub("abc");
        demo.setDate(new Date());
        demo.setXyz(38999.400003433D);



        // 生成动态 Bean
        CglibBean bean = new CglibBean(propertyMap);// tcp推送给客户的数据

//        // 给 Bean 设置值
//        bean.setValue("id", 123);
//        bean.setValue("name", "454");
//        bean.setValue("address", "789");
//
//        // 从 Bean 中获取值，当然了获得值的类型是 Object
//        System.out.println("id      = " + bean.getValue("id"));
//        System.out.println("name    = " + bean.getValue("name"));
//        System.out.println("address = " + bean.getValue("address"));

        // 获得bean的实体
        Object object = bean.getObject();// 推送记录
        BeanUtils.copyProperties(demo,object);


        Class clazz = object.getClass();
        String jsonString = JSON.toJSONString(object);
        Map<String, Object> map = new HashMap<>();
        map.put("sub","abc");
        map.put("date",new Date());
        map.put("xyz",38999.43433D);
        log.info("jsonString:{}",map);
        log.info("jsonString2:{}",jsonString);
//        System.out.println(clazz.getName());
//        // 查看生成的类名
//        System.out.println(clazz.getName());
//        // 通过反射查看所有方法名
//        Method[] methods = clazz.getDeclaredMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
    }
}
