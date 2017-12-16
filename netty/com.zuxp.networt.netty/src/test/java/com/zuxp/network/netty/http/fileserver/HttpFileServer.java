package com.zuxp.network.netty.http.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import com.zuxp.network.netty.http.fileserver.handler.HttpFileServerHandler;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class HttpFileServer {

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
//
                            socketChannel.pipeline().addLast("http decoder",
                                    new HttpRequestDecoder());
                            socketChannel.pipeline().addLast("http aggregator",
                                    new HttpObjectAggregator(65536));
                         

                            socketChannel.pipeline().addLast("http encoder",
                                    new HttpResponseEncoder());
//                            socketChannel.pipeline().addLast("http-Chunked",
//                                    new ChunkedWriteHandler());
//                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new HttpFileServerHandler());

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
