package javaEE.springboot.springbootdemo.beans;

public class FirstBean {
    private String name;
    private int age;

    public FirstBean(){
        System.out.println("Default constructor");
        this.name = "No Name";
        this.age = 0;
    }

    public FirstBean(String name, int age){
        System.out.println("Parametrized constructor");
        this.name = name;
        this.age = age;
    }

    public String getText(){
        return this.name + " " + this.age + " old";
    }

}
