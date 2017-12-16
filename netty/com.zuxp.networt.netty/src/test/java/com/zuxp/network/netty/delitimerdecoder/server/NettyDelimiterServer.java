package com.zuxp.network.netty.delitimerdecoder.server;

import com.zuxp.network.netty.delitimerdecoder.server.handler.DelimiterServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * Created by zuxiangpeng on 2017/11/4.
 */
public class NettyDelimiterServer {

    public static void main(String[] args){

        NioEventLoopGroup bootGroup = new NioEventLoopGroup(1);

        NioEventLoopGroup workerGroup = new NioEventLoopGroup(10);

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bootGroup,workerGroup);
            serverBootstrap.option(ChannelOption.SO_BACKLOG,1024);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ByteBuf buf = Unpooled.wrappedBuffer("$".getBytes());

                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                    socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    socketChannel.pipeline().addLast(new DelimiterServerHandler());
                }

            });

           ChannelFuture f =  serverBootstrap.bind(8082).sync();
           f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bootGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }



}
