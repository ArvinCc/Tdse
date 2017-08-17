package com.tdse.mx.server;

import com.tdse.mx.handler.TdseSocketHandler;
import com.tdse.mx.log.TestLog;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Created by Administrator on 2017/7/17.
 */
public class SocketServer extends Thread
{

    private int port;

    public SocketServer(int port) {
        this.port = port;
    }

    public void run()
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TdseSocketHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync();
            //FileDemo.getInstance().Into("Server Starting" + port);
            TestLog.getInstance().WriteServiceLog("Socket Server: bind is"+port+"pror! Success!");
            f.channel().closeFuture().sync();
        }catch (Exception e)
        {
            TestLog.getInstance().WriteErrorLog("Socket Server: bind is"+port+"pror! Fail!");
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
