package com.zuxp.serialization;

import com.zuxp.serialization.pojo.Student;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MessagePackTest {

    public static void main(String[] args) throws IOException {
        Student stu = new Student();
        stu.setAge(40);
        stu.setName("小名同学");
        stu.setSex("男买饮料的");

        MessagePack pack = new MessagePack();

        byte[] bytes = pack.write(stu);

        Student stu2 = pack.read(bytes,Student.class);

        System.out.println(stu2.toString()+ bytes.length);
        System.out.println(stu == stu2);



    }
}
