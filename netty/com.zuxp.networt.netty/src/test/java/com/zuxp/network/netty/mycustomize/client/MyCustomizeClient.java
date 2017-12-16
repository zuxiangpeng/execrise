package com.zuxp.network.netty.mycustomize.client;

import com.zuxp.network.netty.mycustomize.client.handler.MyCustomizeClientHandler;
import com.zuxp.network.netty.mycustomize.coder.MyCustomizeDeocder;
import com.zuxp.network.netty.mycustomize.coder.MyCustomizeEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MyCustomizeClient {
	public static void main(String[] args) throws Exception {
		
		NioEventLoopGroup worker = new NioEventLoopGroup(2);
		
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(worker);
		bootstrap.channel(NioSocketChannel.class);
		
		bootstrap.option(ChannelOption.TCP_NODELAY,true);
		
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast("frame Decoder",
                        new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
				ch.pipeline().addLast(new  MyCustomizeDeocder());
				
				ch.pipeline().addLast("frame Encoder",
                        new LengthFieldPrepender(2));
				ch.pipeline().addLast(new  MyCustomizeEncoder());
				
				ch.pipeline().addLast( new MyCustomizeClientHandler());

				
			}
		});
		
		ChannelFuture f = bootstrap.connect("localhost",8085).sync();
		
		 f.channel().closeFuture().sync();
	}

}
