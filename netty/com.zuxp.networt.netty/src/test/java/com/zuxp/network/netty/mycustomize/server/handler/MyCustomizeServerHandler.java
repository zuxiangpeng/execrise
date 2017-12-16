package com.zuxp.network.netty.mycustomize.server.handler;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.zuxp.network.netty.mycustomize.coder.MyProtocol;

public class MyCustomizeServerHandler extends SimpleChannelInboundHandler<MyProtocol>{
	
	private AtomicInteger count = new AtomicInteger(0);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MyProtocol msg)
			throws Exception {

		System.out.println("第"+count.getAndAdd(1) +"次");
		System.out.println(msg.getHeader());
		
		System.out.println(msg.getBody());
	}
	


}
