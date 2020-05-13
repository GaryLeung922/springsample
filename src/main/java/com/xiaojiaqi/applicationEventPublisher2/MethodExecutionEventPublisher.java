package com.xiaojiaqi.applicationEventPublisher2;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/14 8:50 AM
 */
public class MethodExecutionEventPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public void methodToMonitor(){
        MethodExecutionEvent begin = new MethodExecutionEvent(this,"methodToMonitor", MethodExecutionStatus.BEGIN);
        eventPublisher.publishEvent(begin);

        System.out.println("Publisher handle");

        MethodExecutionEvent end = new MethodExecutionEvent(this,"methodToMonitor", MethodExecutionStatus.END);
        eventPublisher.publishEvent(end);
    }
}
