package com.tdse.mx.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

/**
 * Created by Administrator on 2017/7/15.
 */
public class TdseHttpInitializer extends ChannelInitializer<SocketChannel>
{

    private final SslContext sslCtx;

    public TdseHttpInitializer(SslContext sslCtx)
    {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception
    {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new TdseHttpFileHandler());
        pipeline.addLast(new TdseHttpHandler());
    }

}
