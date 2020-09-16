package com.xiaojiaqi.messageSource;

import com.xiaojiaqi.applicationEventPublisher2.MethodExecutionEventPublisher;
import com.xiaojiaqi.config.MainConfig;
import com.xiaojiaqi.service.SpitterService;
import com.xiaojiaqi.tx.TxConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) throws Throwable {


        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
        SpitterService spitterService = ac.getBean(SpitterService.class);

        spitterService.insert();

        ac.close();




    }
}
