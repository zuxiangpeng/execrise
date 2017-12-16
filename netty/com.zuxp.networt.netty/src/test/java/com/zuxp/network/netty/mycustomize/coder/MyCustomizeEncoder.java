package com.zuxp.network.netty.mycustomize.coder;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyCustomizeEncoder  extends MessageToByteEncoder<MyProtocol>{

	@Override
	protected void encode(ChannelHandlerContext ctx, MyProtocol msg, ByteBuf out)
			throws Exception {
		out.writeCharSequence(msg.getHeader(), Charset.forName("UTF-8"));
		
		out.writeCharSequence(msg.getBody(), Charset.forName("UTF-8"));
		
	}

}
