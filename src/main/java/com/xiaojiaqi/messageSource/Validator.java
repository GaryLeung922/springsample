package com.xiaojiaqi.messageSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Locale;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/13 1:15 PM
 */
public class Validator implements InitializingBean, DisposableBean {
    private MessageSource messageSource;

    public String validate(){
        return messageSource.getMessage("menu.file",new Object[]{"FF"}, Locale.CHINA);
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(".1destroy: "+messageSource.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(messageSource == null){
            System.out.println("messageSource is null");
        }else{
            System.out.println("messageSource isn't null, after propertiesSet :");

        }
    }

    @PostConstruct
    public void post(){
        if(messageSource == null){
            System.out.println("2.messageSource is null, 2");
        }else{
            System.out.println("2 messageSource isn't null, after propertiesSet :");

        }
    }

    @PreDestroy
    public void de(){
        System.out.println(".2destroy: "+messageSource.toString());
    }
}
