package com.zuxp.network.netty.mycustomize.coder;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MyCustomizeDeocder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		byte[]  header = new byte[64];
		in.readBytes(header);
		
		byte[] body = new byte[in.readableBytes()];
		
		in.readBytes(body);
		
		MyProtocol myProtocol = new MyProtocol();
		
		myProtocol.setHeader(new String(header, "UTF-8"));
		
		myProtocol.setBody(new String(body, "UTF-8"));
		
		out.add(myProtocol);
		
	}

}
