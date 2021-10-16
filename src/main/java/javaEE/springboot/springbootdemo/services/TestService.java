package javaEE.springboot.springbootdemo.services;

public interface TestService {

    String getTestData();
    int getTestDataInt();
    void setTestData(String testData);

    boolean auth(String email, String password);

}
