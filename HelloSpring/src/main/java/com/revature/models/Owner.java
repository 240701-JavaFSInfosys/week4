package com.revature.models;

import org.springframework.stereotype.Component;

@Component //1 of the 4 Stereotype Annotations - makes a class a bean
public class Owner {

    private String name;
    private int age;


    /*boilerplate code---------------------------
     -no args, all args, getter/setter, toString() */

    public Owner() {
    }

    public Owner(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
