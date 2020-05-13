package com.xiaojiaqi.pojo;

import lombok.Data;
import org.springframework.aop.framework.AopContext;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/23 12:36 PM
 */
@Data
public class NestableInvocationBO {
    public void method1(){

        ((NestableInvocationBO)AopContext.currentProxy()).method2();
        System.out.println("method1 executed. ");
    }

    public void method2(){
        System.out.println("method2 executed. ");
    }
}
