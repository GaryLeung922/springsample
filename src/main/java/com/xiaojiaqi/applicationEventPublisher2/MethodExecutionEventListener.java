package com.xiaojiaqi.applicationEventPublisher2;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/14 8:46 AM
 */
public class MethodExecutionEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof MethodExecutionEvent){
            System.out.println("Listener handle.");
        }
    }
}
