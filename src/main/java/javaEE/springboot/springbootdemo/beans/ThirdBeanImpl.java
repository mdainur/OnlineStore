package javaEE.springboot.springbootdemo.beans;

import org.springframework.stereotype.Component;

//@Component
public class ThirdBeanImpl implements ThirdBean{

    private String data;

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
