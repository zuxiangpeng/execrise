package com.zuxp.network.netty.msgpack.pojo;

import org.msgpack.annotation.Message;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
@Message
public class Person {

    private String name;

    private String age;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
