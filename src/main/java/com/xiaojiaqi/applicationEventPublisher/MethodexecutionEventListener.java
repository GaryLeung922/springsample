package com.xiaojiaqi.applicationEventPublisher;

import java.util.EventListener;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/13 1:37 PM
 */
public interface MethodexecutionEventListener extends EventListener {

    void onMethodBegin(MethodexecutionEvent evt);

    void onMethodEnd(MethodexecutionEvent evt);
}
