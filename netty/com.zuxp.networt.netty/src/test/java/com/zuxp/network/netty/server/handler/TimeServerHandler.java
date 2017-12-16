package com.zuxp.network.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DateFormatter;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by zuxiangpeng on 2017/11/4.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

     AtomicInteger  count = new AtomicInteger(1);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

//        ByteBuf msgBuffer = (ByteBuf) msg;
//
//        byte[] buf = new byte[msgBuffer.readableBytes()];
//        msgBuffer.readBytes(buf);

        System.out.println(String.format("收到消息%s, 第%s次\n",msg,count.getAndAdd(1)));

//        ByteBuffer  resp = ByteBuffer.allocate(512);
//
//        resp.put(String.format("time is now:%s", DateFormatter.format(new Date(System.currentTimeMillis()))).getBytes("UTF-8"));
//        ByteBuf byteBuf = Unpooled.wrappedBuffer(resp);

//        byte[] msgByte = String.format("time is now:%s\n", DateFormatter.format(new Date(System.currentTimeMillis()))).getBytes("UTF-8");
        byte[] msgByte = String.format("time is now:%s$", DateFormatter.format(new Date(System.currentTimeMillis()))).getBytes("UTF-8");

        ByteBuf byteBuf = Unpooled.buffer(msgByte.length);
        byteBuf.writeBytes(msgByte);

        ctx.writeAndFlush(byteBuf);
        ctx.flush();
    }
}
