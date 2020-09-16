package com.xiaojiaqi.circurImport;

import com.xiaojiaqi.config.MainConfig;
import com.xiaojiaqi.tx.TxConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: liangjiaqi
 * @Date: 2020/9/14 6:38 PM
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);

        A bean = ac.getBean(A.class);
    }
}
