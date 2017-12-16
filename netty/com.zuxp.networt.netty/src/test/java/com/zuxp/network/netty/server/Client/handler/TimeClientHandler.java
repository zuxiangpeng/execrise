package com.zuxp.network.netty.server.Client.handler;

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
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    AtomicInteger count = new AtomicInteger(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {


        byte[] msgByte = "现在时间是多少$".getBytes("UTF-8");
        for (int i= 0; i<100; i++) {
            ByteBuf byteBuf = Unpooled.buffer(msgByte.length);
            byteBuf.writeBytes(msgByte);

            ctx.writeAndFlush(byteBuf);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        ByteBuf msgBuffer = (ByteBuf) msg;
//
//        byte[] buf = new byte[msgBuffer.readableBytes()];
//        msgBuffer.readBytes(buf);

        System.out.println(String.format("收到消息%s, 第%s次\n",msg,count.getAndAdd(1)));
//
//        ByteBuffer resp = ByteBuffer.allocate(512);
//
////        resp.put("现在时间是多少".getBytes("UTF-8"));
//        Thread.sleep(1000);
//        byte[] msgByte = "现在时间是多少".getBytes("UTF-8");
//        ByteBuf byteBuf = Unpooled.buffer(msgByte.length);
//        byteBuf.writeBytes(msgByte);
//
//        ctx.writeAndFlush(byteBuf);
    }

}
