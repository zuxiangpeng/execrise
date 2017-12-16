package com.zuxp.network.netty.delitimerdecoder.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class NettDelimiterClient {

    public static void  main(String []args){

        NioEventLoopGroup bootGroup = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(bootGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ByteBuf buf = Unpooled.wrappedBuffer("$".getBytes());

                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                    socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    socketChannel.pipeline().addLast(new DelimiterClientHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("localhost",8082);

            ChannelFuture f =channelFuture.sync();
            System.out.println(f== channelFuture);
            f.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
