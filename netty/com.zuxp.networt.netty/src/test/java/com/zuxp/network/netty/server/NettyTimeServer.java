package com.zuxp.network.netty.server;

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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by zuxiangpeng on 2017/11/4.
 */
public class NettyTimeServer {
    public static  void main(String[] args){

        NioEventLoopGroup bootGroup = new NioEventLoopGroup(1);

        NioEventLoopGroup worker = new NioEventLoopGroup(10);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bootGroup, worker).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 512)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                              byte[] fgf = "$".getBytes();
                            ByteBuf buf = Unpooled.copiedBuffer(fgf);
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new TimeServerHandler());
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
