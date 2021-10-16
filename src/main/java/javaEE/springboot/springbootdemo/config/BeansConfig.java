package javaEE.springboot.springbootdemo.config;

import javaEE.springboot.springbootdemo.beans.FirstBean;
import javaEE.springboot.springbootdemo.beans.ThirdBean;
import javaEE.springboot.springbootdemo.beans.ThirdBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeansConfig {

    @Bean
    public FirstBean firstBean(){
        return new FirstBean();
    }

    @Bean
    public FirstBean secondBean(){
        return new FirstBean("Ulpan", 10);
    }

    @Bean
    public ThirdBean thirdBean(){
        return new ThirdBeanImpl();
    }

}
