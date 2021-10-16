package javaEE.springboot.springbootdemo.services.impl;

import javaEE.springboot.springbootdemo.services.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private String testData;
    private int testDataInt;

    @Override
    public String getTestData() {
        return this.testData;
    }

    @Override
    public int getTestDataInt() {
        return this.testDataInt;
    }

    @Override
    public void setTestData(String testData) {
        this.testData = testData;
    }

    @Override
    public boolean auth(String email, String password) {
        return false;
    }
}
