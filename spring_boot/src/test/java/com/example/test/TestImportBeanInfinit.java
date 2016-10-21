package com.example.test;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by zhougb on 2016/8/11.
 */
public class TestImportBeanInfinit implements ImportBeanDefinitionRegistrar{
    private static final String BEAN_NAME = "person";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (!registry.containsBeanDefinition(BEAN_NAME)) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(Person.class);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            // We don't need this one to be post processed otherwise it can cause a
            // cascade of bean instantiation that we would rather avoid.
            beanDefinition.setSynthetic(true);
            registry.registerBeanDefinition(BEAN_NAME, beanDefinition);
        }
    }
}
