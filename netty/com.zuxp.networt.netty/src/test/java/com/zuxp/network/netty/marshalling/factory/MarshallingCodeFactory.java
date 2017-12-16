package com.zuxp.network.netty.marshalling.factory;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * Created by zuxiangpeng on 2017/11/5.
 */
public class MarshallingCodeFactory {

    public static MarshallingEncoder buildMashallingEncoder(){


        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final  MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        MarshallerProvider provider = new DefaultMarshallerProvider(factory, configuration);

        MarshallingEncoder encoder = new MarshallingEncoder(provider);
        return encoder;
    }


    public static MarshallingDecoder buildMashallingDecoder(){

        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");

        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        int maxSize = 1024 << 2;
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, configuration);

        MarshallingDecoder decoder = new MarshallingDecoder(provider, maxSize);
        return decoder;
    }
}
