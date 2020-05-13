package com.xiaojiaqi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/26 8:02 AM
 */
@Service
public class SimpleService {


    public void simple(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();

        definition.setTimeout(20);

    }
}
