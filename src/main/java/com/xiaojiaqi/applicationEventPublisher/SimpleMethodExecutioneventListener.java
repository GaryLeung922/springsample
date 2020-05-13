package com.xiaojiaqi.applicationEventPublisher;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/13 1:39 PM
 */
public class SimpleMethodExecutioneventListener implements MethodexecutionEventListener {
    @Override
    public void onMethodBegin(MethodexecutionEvent evt) {
        System.out.println("start: "+ evt.getMethodName());
    }

    @Override
    public void onMethodEnd(MethodexecutionEvent evt) {
        System.out.println("finished: "+ evt.getMethodName());
    }
}
