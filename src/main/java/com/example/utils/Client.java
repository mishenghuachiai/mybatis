package com.example.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class Client implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
    public static void main(String[] args) {
        System.out.println("1111");
    }

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
