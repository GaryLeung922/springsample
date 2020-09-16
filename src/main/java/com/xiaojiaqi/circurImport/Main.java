package com.xiaojiaqi.circurImport;

/**
 *
 * 循环依赖导致栈溢出
 * @Author: liangjiaqi
 * @Date: 2020/9/14 6:26 PM
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(new A());
    }
    static class A {
        public A() {
            new B();
        }
    }
    static class B {
        public B() {
            new A();
        }
    }

}



