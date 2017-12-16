package com.zuxp.network.netty.server.Client;

import com.zuxp.network.netty.server.Client.handler.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by zuxiangpeng on 2017/11/4.
 */
public class NettTimeClient {

    public static void main(String[] args){

        NioEventLoopGroup bootGrop = new NioEventLoopGroup(1);

        try{
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(bootGrop).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {


                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    byte[] fgf = "$".getBytes();
                    ByteBuf buf = Unpooled.copiedBuffer(fgf);
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));

                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new TimeClientHandler());
                }
            });
            ChannelFuture f = bootstrap.connect("localhost",8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }

    }
}
