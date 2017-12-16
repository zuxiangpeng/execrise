package com.zuxp.network.netty.marshalling.pojo;

import java.io.Serializable;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 3243874600034040203L;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("name:%s", this.name);
    }
}
