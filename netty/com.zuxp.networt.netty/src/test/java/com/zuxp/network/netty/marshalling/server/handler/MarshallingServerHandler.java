package com.zuxp.network.netty.marshalling.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MarshallingServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg+"hahaaa");
    }
}
