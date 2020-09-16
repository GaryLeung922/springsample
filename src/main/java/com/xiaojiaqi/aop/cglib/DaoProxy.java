package com.xiaojiaqi.aop.cglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: liangjiaqi
 * @Date: 2020/9/16 2:15 PM
 */
public class DaoProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before method invoke");
        methodProxy.invoke(o,objects);
        System.out.println("After method invoke");
        return o;
    }
}
