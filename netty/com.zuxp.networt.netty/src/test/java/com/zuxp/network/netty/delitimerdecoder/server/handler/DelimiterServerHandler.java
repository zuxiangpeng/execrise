package com.zuxp.network.netty.delitimerdecoder.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class DelimiterServerHandler extends ChannelInboundHandlerAdapter {

    AtomicInteger count = new AtomicInteger(1);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String msgStr = (String)msg;

        System.out.println(String.format(("receive messgae：%s；第%s次"), msgStr, count.getAndAdd(1)));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");

        String resp = String.format("time is now %s。$", simpleDateFormat.format(new Date(System.currentTimeMillis())));
        ByteBuf buf = Unpooled.wrappedBuffer(resp.getBytes("UTF-8"));

        ctx.writeAndFlush(buf);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
         System.out.println("这个什么时候调用呢");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        System.exit(1);
     }
}
