package com.zuxp.network.netty.marshalling.server;

import com.zuxp.network.netty.marshalling.factory.MarshallingCodeFactory;
import com.zuxp.network.netty.marshalling.server.handler.MarshallingServerHandler;
import com.zuxp.network.netty.server.handler.TimeServerHandler;
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

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MarshingServer {
    public static  void main(String[] args){

        NioEventLoopGroup bootGroup = new NioEventLoopGroup(1);

        NioEventLoopGroup worker = new NioEventLoopGroup(10);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bootGroup, worker).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(MarshallingCodeFactory.buildMashallingDecoder());
                            socketChannel.pipeline().addLast(MarshallingCodeFactory.buildMashallingEncoder());

                            socketChannel.pipeline().addLast(new MarshallingServerHandler());
                        }
                    });
            ChannelFuture f = bootstrap.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bootGroup.shutdownGracefully();

            worker.shutdownGracefully();
        }

    }

}
