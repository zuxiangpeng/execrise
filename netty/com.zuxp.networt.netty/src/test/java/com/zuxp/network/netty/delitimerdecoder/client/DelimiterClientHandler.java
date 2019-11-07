package com.zuxp.network.netty.delitimerdecoder.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class DelimiterClientHandler extends ChannelInboundHandlerAdapter {

    AtomicInteger  count = new AtomicInteger(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] msg = "what is time now$".getBytes();
        for (int i =0; i<1000; i++){
            ByteBuf buf = Unpooled.wrappedBuffer(msg);
            ctx.writeAndFlush(buf);
        }

        BigDecimal.valueOf(12L).intValue();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String msgStr = String.valueOf(msg);

        System.out.println(String.format("第%s次收到消息%s",count.getAndAdd(1), msgStr));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
