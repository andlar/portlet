package org.pulseus.auth.portlet.config;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.web.portlet.context.AbstractRefreshablePortletApplicationContext;

public class AnnotationConfigurationPortletContext extends AbstractRefreshablePortletApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        loadConfigBeanDefinitions(beanFactory);
    }

    private void loadConfigBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        for (String configurationClassName : getConfigLocations()) {
            BeanDefinition configBeanDefinition = new GenericBeanDefinition();
            configBeanDefinition.setBeanClassName(configurationClassName);
            loadConfigBeanDefinition(configBeanDefinition, beanFactory);
        }
    }

    private void loadConfigBeanDefinition(BeanDefinition beanDefinition, DefaultListableBeanFactory beanFactory) {
        beanFactory.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
    }

}
