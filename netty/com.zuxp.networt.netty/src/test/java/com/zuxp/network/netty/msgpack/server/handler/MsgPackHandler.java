package com.zuxp.network.netty.msgpack.server.handler;

import com.zuxp.network.netty.msgpack.pojo.Person;
import com.zuxp.network.netty.msgpack.pojo.Response;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DateFormatter;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MsgPackHandler extends ChannelInboundHandlerAdapter{

    AtomicInteger count = new AtomicInteger(1);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        MessagePack messagePack = new MessagePack();
        Person person =  messagePack.convert((Value) msg, Person.class);
        int c = count.get();
        System.out.println(person.toString() +"第"+ count.getAndAdd(1));

        Response resp = new Response();

        resp.setClassName(Person.class.getName());

        resp.setMsg("收到了"+c);

        ctx.write(resp);
        ctx.flush();



    }
}
