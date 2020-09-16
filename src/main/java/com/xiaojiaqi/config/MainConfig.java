package com.xiaojiaqi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.xiaojiaqi.external.A;
import com.xiaojiaqi.external.B;
import com.xiaojiaqi.messageSource.Validator;
import com.xiaojiaqi.pojo.NestableInvocationBO;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/18 12:45 PM
 */
@Configuration
@ComponentScan(
        basePackages = "com.xiaojiaqi"
)
@EnableAspectJAutoProxy(exposeProxy = true)
@PropertySource("classpath:/app.properties")
public class MainConfig {

    @Autowired
    private Environment env;
    @Bean
    public Validator getValidator(){
        return new Validator();
    }

    @Bean
    public NestableInvocationBO nestableInvocationBO(){
        return new NestableInvocationBO();
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource bds = new DruidDataSource();
        bds.setPassword(env.getProperty("jdbc.password"));
        bds.setUsername(env.getProperty("jdbc.username"));
        bds.setUrl(env.getProperty("jdbc.url"));
        bds.setMaxActive(1000);
        bds.setMinEvictableIdleTimeMillis(600);
        bds.setInitialSize(10);
        bds.setMinIdle(10);
        return bds;
    }

    @Bean
    public A a(){
        return new A();
    }

    @Bean
    public B b(){
        return new B();
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.xiaojiaqi");
//        factory.setPersistenceProvider(new HibernatePersistenceProvider());
//
//        factory.setDataSource(dataSource());
//        return factory;
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }

}
