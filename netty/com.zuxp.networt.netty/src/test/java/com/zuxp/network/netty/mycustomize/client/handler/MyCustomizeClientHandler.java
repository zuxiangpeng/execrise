package com.zuxp.network.netty.mycustomize.client.handler;

import com.zuxp.network.netty.mycustomize.coder.MyProtocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyCustomizeClientHandler extends ChannelInboundHandlerAdapter{


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		for(int j = 0; j<2000; j++){
			MyProtocol myProtocol = new MyProtocol();
			
			byte header[] = new byte[64];
			
			for (int i = 0; i< 64; i++){
				header[i] = (byte) (45+i);
			}
			
			myProtocol.setHeader(new String(header, "UTF-8"));		
			myProtocol.setBody("ahahaahh我来了:"+j);
			ctx.writeAndFlush(myProtocol);
		}

		
	}
}
