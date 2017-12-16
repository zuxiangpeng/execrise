package com.zuxp.network.netty.msgpack.client;

import com.zuxp.network.netty.msgpack.client.handler.MsgPackClentHandler;
import com.zuxp.network.netty.server.Client.handler.TimeClientHandler;
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
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.msgpack.MsgPackDecoder;
import io.netty.handler.codec.msgpack.MsgpackEncoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MsgPackClient {

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


                    // 增加长度解码器
                            socketChannel.pipeline().addLast("frame Decoder",
                                    new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                    socketChannel.pipeline().addLast("messagePack Decoder", new MsgPackDecoder());

                            socketChannel.pipeline().addLast("frame Encoder",
                                    new LengthFieldPrepender(2));

                    socketChannel.pipeline().addLast("messagePack Encoder",new MsgpackEncoder());

                    socketChannel.pipeline().addLast(new MsgPackClentHandler());
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
