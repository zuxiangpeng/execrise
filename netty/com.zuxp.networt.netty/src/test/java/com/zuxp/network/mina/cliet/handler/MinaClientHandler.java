package com.zuxp.network.mina.cliet.handler;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 客户端业务处理逻辑
 */
public class MinaClientHandler extends IoHandlerAdapter {
    // 当客户端连接进入时  
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("incomming 客户端: " + session.getRemoteAddress());
        session.write("i am coming");


    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("客户端发送信息异常....");
    }

    // 当客户端发送消息到达时  
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println(session);
        System.out.println("服务器返回的数据：" + message.toString());

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("客户端与服务端断开连接.....");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {

        System.out.println("one Client Connection" + session.getRemoteAddress());
        session.write("我来了······");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        IoBuffer buf=IoBuffer.allocate(256);//buffer max size
            buf.put("I am gggg".getBytes("UTF-8"));
        buf.flip();
        session.write(buf);
        System.out.println("client发送信息" + message.toString());
    }

}
