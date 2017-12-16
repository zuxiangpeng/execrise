package com.zuxp.network.netty.msgpack.client.handler;

import com.zuxp.network.netty.msgpack.pojo.Person;
import com.zuxp.network.netty.msgpack.pojo.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MsgPackClentHandler extends ChannelInboundHandlerAdapter {

	AtomicInteger count = new AtomicInteger(1);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Person person = new Person();
		person.setAge("25");
		person.setName("海之言");
		person.setSex("老爷们");
		for (int i = 0; i < 1000; i++) {
			ctx.write(person);
			ctx.flush();
		}

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		MessagePack messagePack = new MessagePack();
		Response person = messagePack.convert((Value) msg, Response.class);
		System.out.println(person.toString() + count.getAndAdd(1));
	}

}
