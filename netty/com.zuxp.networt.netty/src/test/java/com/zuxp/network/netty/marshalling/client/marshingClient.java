package com.zuxp.network.netty.marshalling.client;

import com.zuxp.network.netty.marshalling.client.handler.MarshallingClentHandler;
import com.zuxp.network.netty.marshalling.factory.MarshallingCodeFactory;
import com.zuxp.network.netty.msgpack.client.handler.MsgPackClentHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.msgpack.MsgPackDecoder;
import io.netty.handler.codec.msgpack.MsgpackEncoder;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class marshingClient {

    public static void main(String[] args){

        NioEventLoopGroup bootGrop = new NioEventLoopGroup(1);

        try{
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(bootGrop)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {


                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {


                            socketChannel.pipeline().addLast(MarshallingCodeFactory.buildMashallingDecoder());

                            socketChannel.pipeline().addLast(MarshallingCodeFactory.buildMashallingEncoder());

                            socketChannel.pipeline().addLast(new MarshallingClentHandler());
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
