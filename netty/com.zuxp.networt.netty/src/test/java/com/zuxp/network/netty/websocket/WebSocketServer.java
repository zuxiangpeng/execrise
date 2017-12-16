package com.zuxp.network.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {
	
	public static void main(String[] args) throws Exception {
		
		NioEventLoopGroup  boos = new NioEventLoopGroup(2);
		
		NioEventLoopGroup worker = new NioEventLoopGroup(10);
		
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		bootstrap.group(boos, worker);
		
		bootstrap.channel(NioServerSocketChannel.class);
		
		bootstrap.childHandler( new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new HttpServerCodec());
				ch.pipeline().addLast(new HttpObjectAggregator(65535));
				ch.pipeline().addLast(new ChunkedWriteHandler());
//				ch.pipeline().addLast(new WebSocketServerHandler());
			}
		});
		
		ChannelFuture f = bootstrap.bind(8088).sync();
		f.channel().close().sync();
	}

}
