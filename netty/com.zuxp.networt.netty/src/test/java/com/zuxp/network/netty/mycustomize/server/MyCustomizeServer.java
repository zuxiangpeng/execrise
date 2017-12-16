package com.zuxp.network.netty.mycustomize.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import com.zuxp.network.netty.mycustomize.coder.MyCustomizeDeocder;
import com.zuxp.network.netty.mycustomize.coder.MyCustomizeEncoder;
import com.zuxp.network.netty.mycustomize.server.handler.MyCustomizeServerHandler;

public class MyCustomizeServer {
	
	public static void main(String[] args) throws Exception {
		
		NioEventLoopGroup boos = new NioEventLoopGroup(2);
		
		NioEventLoopGroup worker = new NioEventLoopGroup(10);
		
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		bootstrap.group(boos, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.SO_BACKLOG, 512);
		bootstrap.childHandler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast("frame Decoder",
                        new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
				ch.pipeline().addLast(new  MyCustomizeDeocder());
				
				ch.pipeline().addLast("frame Encoder",
                        new LengthFieldPrepender(2));
				ch.pipeline().addLast(new  MyCustomizeEncoder());

				ch.pipeline().addLast( new MyCustomizeServerHandler());
			}
		});
		
		ChannelFuture future = bootstrap.bind(8085).sync();
		future.channel().closeFuture().sync();
		
		
		
	}

}
