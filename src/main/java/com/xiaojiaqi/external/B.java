package com.xiaojiaqi.external;

/**
 * @Author: liangjiaqi
 * @Date: 2020/5/21 5:25 PM
 */
public class B extends A {

    @Override
    public void a() {
        super.a();
        System.out.println("b");
    }

    public void b(){
        System.out.println("b");
    }
}
