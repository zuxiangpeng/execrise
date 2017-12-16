package com.zuxp.serialization.pojo;

import org.msgpack.annotation.Message;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
@Message
public class Student {

    private Integer age;

    private  String name;

    private String sex;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return String.format("name:%s;age:%s;sex:%s", this.name,this.age, this.sex);
    }
}


