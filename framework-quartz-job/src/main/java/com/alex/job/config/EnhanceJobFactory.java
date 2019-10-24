package com.alex.job.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * Quartz 增强JobFactory
 *
 *  继承至  AdaptableJobFactory 为了解决在job业务类中无法注入Spring 管理的Bean问题
 *
 *      当然可以使用另外一个方式，在需要使用到对应Bean的地方采用封装的SpringBeanUtil来获取对应的Bean，这只是另外一种扩展方式
 */
@Component
public class EnhanceJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        autowireCapableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
