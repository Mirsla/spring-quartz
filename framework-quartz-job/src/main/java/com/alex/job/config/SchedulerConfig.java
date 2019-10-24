package com.alex.job.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    @Autowired
    private EnhanceJobFactory enhanceJobFactory;


    @Bean(name="SchedulerFactory")
    @ConditionalOnMissingBean(SchedulerFactoryBean.class)
    @ConditionalOnProperty(prefix = "qx.job", name = "mode", havingValue = "RAM", matchIfMissing = true)
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 指定自定义的增强JobFactory，解决job业务类中不能注入Bean的问题
        factory.setJobFactory(enhanceJobFactory);
        return factory;
    }

    @Bean(name="SchedulerFactory")
    @ConditionalOnMissingBean(SchedulerFactoryBean.class)
    @ConditionalOnProperty(prefix = "qx.job", name = "mode", havingValue = "CUSTOM_JX")
    public SchedulerFactoryBean customSchedulerFactoryBean(){
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(enhanceJobFactory);
        return factory;
    }

    @Bean(name="SchedulerFactory")
    @ConditionalOnProperty(prefix = "qx.job", name = "mode", havingValue = "DEFAULT_JX")
    @ConditionalOnMissingBean(SchedulerFactoryBean.class)
    public SchedulerFactoryBean JxSchedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());

        // 指定自定义的增强JobFactory，解决job业务类中不能注入Bean的问题
        factory.setJobFactory(enhanceJobFactory);
        return factory;
    }

    /**
     * 只有在默认的持久化状态下才加载 quartz.properties
     * @return Properties quartz
     */
    @Bean
    @ConditionalOnProperty(prefix = "qx.job", name = "mode", havingValue = "DEFAULT_JX")
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * quartz初始化监听器
     * @return QuartzInitializerListener Quartz监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     * @return  Scheduler
     */
    @Bean(name="Scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
