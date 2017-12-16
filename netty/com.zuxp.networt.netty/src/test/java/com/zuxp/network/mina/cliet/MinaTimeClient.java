package com.zuxp.network.mina.cliet;

import java.net.InetSocketAddress;

import com.zuxp.network.mina.cliet.handler.MinaClientHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


public class MinaTimeClient {

    private static final long CONNECT_TIMEOUT = 0;

    private static final boolean USE_CUSTOM_CODEC = true;

    public static void main(String[] args) throws Throwable {




        //Create TCP/IP connection     
        NioSocketConnector connector = new NioSocketConnector();

        //创建接受数据的过滤器     
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();

        //设定这个过滤器将一行一行(/r/n)的读取数据     
        chain.addLast("myChin", new ProtocolCodecFilter(new TextLineCodecFactory()));

        //客户端的消息处理器：一个SamplMinaServerHander对象     
        connector.setHandler(new MinaClientHandler());

        //set connect timeout     
        connector.setConnectTimeoutMillis(1000 * 60);

        //连接到服务器：     
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", 8080));


        //Wait for the connection attempt to be finished.     
        cf.awaitUninterruptibly();

        cf.getSession().getCloseFuture().awaitUninterruptibly();
        IoSession session = cf.getSession();
        System.out.println(session);
//        Charset charset = Charset.forName("utf-8");
//        while (true) {
////            IoBuffer buf=IoBuffer.allocate(256);//buffer max size
////            buf.put("I am gggg".getBytes("UTF-8"));
//            WriteFuture writeFeature = session.write("I am gggg");
////            writeFeature.getSession().
//            Thread.sleep(10000);
//        }
        connector.dispose();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("我要被关闭了");
            }
        }));
    }
}
