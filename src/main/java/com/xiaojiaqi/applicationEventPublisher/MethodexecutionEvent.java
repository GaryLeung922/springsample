package com.xiaojiaqi.applicationEventPublisher;

import com.xiaojiaqi.applicationEventPublisher2.MethodExecutionEventPublisher;
import com.xiaojiaqi.applicationEventPublisher2.MethodExecutionStatus;

import java.util.EventObject;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/13 1:34 PM
 */
public class MethodexecutionEvent extends EventObject {

    private String methodName;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MethodexecutionEvent(Object source) {
        super(source);
    }

    public MethodexecutionEvent(Object source, String methodName) {
        super(source);
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
