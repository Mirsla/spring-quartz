package com.alex.job.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * SpringBeanUtil 工具类
 */
@Service
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() throws BeansException {
        return context;
    }

    /**
     * 通过 name从spring容器中获取Bean
     * @param name bean 的name
     * @return object
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 根据 bean type获取bean
     * @param clazz bean 的类型
     * @return 返回对应类型的bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 根据bean的name和type从spring 容器中获取bean
     * @param name bean的name
     * @param clazz bean的类型
     * @param <T> 返回bean的类型
     * @return 对应类型的bean
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return (T) context.getBean(name,clazz);
    }

    /**
     * 通过bean
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type){
        return context.getBeansOfType(type);
    }

}
