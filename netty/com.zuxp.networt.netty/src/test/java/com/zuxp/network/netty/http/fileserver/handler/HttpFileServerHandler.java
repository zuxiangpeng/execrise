package com.zuxp.network.netty.http.fileserver.handler;

import static io.netty.util.internal.ObjectUtil.checkNotNull;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.io.RandomAccessFile;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	
	private static String root = "E:/httptest/";

	@Override
	protected void channelRead0(final ChannelHandlerContext ctx, FullHttpRequest msg)
			throws Exception {
		if(msg.decoderResult().isFailure()){
			//返回失败消息
		}
		
		if(!msg.method().equals(HttpMethod.GET)){
			//返回失败消息
		}
		
		System.out.println(msg.uri());
		
	
		System.out.println(msg.toString());
		
		System.out.println(msg.refCnt());
		
		System.out.println(msg.decoderResult());
		
		System.out.println(msg.headers().toString());
		
//		String path = msg.uri();
//		RandomAccessFile accessFile = null;
//		try{
//			accessFile = new RandomAccessFile(root+path, "r");
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		 public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status,
//		            ByteBuf content, HttpHeaders headers, HttpHeaders trailingHeaders) {
//		        super(version, status, headers);
//		        this.content = checkNotNull(content, "content");
//		        this.trailingHeaders = checkNotNull(trailingHeaders, "trailingHeaders");
//		    }
		
//		Long length = accessFile.length();

		ByteBuf buf = Unpooled.copiedBuffer("{\"dd\":\"22\"}".getBytes("UTF-8"));
		
		HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
		httpResponse.headers().set("Connnection", "close");
		httpResponse.headers().set("Content-Length", buf.array().length);
		httpResponse.headers().set("Content-Type", "application/json");
		httpResponse.headers().set("ddd", "text/xml; charset=utf-8");

//		Accept-Ranges:bytes
//		Age:76604
//		Cache-Control:max-age=315360000
//		Connnection:close
//		Content-Length:6517
//		Content-Type:image/jpeg
//		httpResponse.headers().
//		httpResponse.
//		httpResponse.headers().set("Connection",HttpResponseStatus.);

//		httpResponse.
		
       ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);	
//		ctx.write(LastHttpContent.EMPTY_LAST_CONTENT);
//		ctx.flush();


//		httpResponse.h
//		httpResponse.setDecoderResult(result);
//		Connection:keep-alive
//		Content-Length:240

//		Date:Sat, 02 Dec 2017 12:32:57 GMT
	}
	
	

}
