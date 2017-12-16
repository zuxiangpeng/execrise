package com.zuxp.network.netty.marshalling.client.handler;

import com.zuxp.network.netty.marshalling.pojo.Student;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MarshallingClentHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Student student = new Student();
        student.setName("jaaj");

        ctx.writeAndFlush(student);
    }


}
