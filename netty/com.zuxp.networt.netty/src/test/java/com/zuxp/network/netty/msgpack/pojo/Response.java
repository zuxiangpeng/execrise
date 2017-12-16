package com.zuxp.network.netty.msgpack.pojo;

import org.msgpack.annotation.Message;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
@Message
public class Response {

    private String className;
    private String msg;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
