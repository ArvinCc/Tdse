package com.tdse.mx.handler;

import com.tdse.mx.event.SocketEventManager;
import com.tdse.mx.server.FileDemo;
import com.tdse.mx.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.*;

/**
 * Created by Administrator on 2017/6/15.
 */
public class TdseSocketHandler extends ChannelInboundHandlerAdapter
{
    /**
     *  初始化
     */
    public TdseSocketHandler() {
        super();
    }

    /**
     * 捕获异常
     * @param ctx 通道
     * @param cause 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    /**
     * 通道读取完毕
     * @param ctx 通道
     * @throws Exception 捕获
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 用户事件触发
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    /**
     * 通道的可写性
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception
    {
        super.channelWritabilityChanged(ctx);
    }

    /**
     * 通道的活跃性
     * @param ctx
     */
//    @Override
//    public void channelActive(final ChannelHandlerContext ctx){
//        final ByteBuf time =ctx.alloc().buffer(4);
//        time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
//
//        final ChannelFuture f =ctx.writeAndFlush(time);
//
//        System.out.println("Server return:"+time.toString());
//
//        f.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                assert  f ==future;
//                ctx.close();
//            }
//        });
//    }

    /**
     * 通道增加,新增加用户连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println(ctx.channel().id() + "进来了");
    }

    /**
     * 通道减少,有用户离开了
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println(ctx.channel().id()+"离开了");
    }

    /**
     * 通道注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    /**
     * 通道未注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    /**
     * 通道的活跃性
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 通道读
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
            try {
                ByteBuf buf = (ByteBuf) msg;
                byte[] req = new byte[buf.readableBytes()];
                buf.readBytes(req);
                ByteBuf resp = Unpooled.copiedBuffer(SocketEventManager.getInstance().requestHandler(req));
                //ctx.write(resp);
                ctx.writeAndFlush(resp).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            System.out.println("写完了");
                        } else {
                            System.out.println("写失败了");
                        }
                    }
                });
            }catch (Exception e)
            {
                FileDemo.getInstance().Into("Socket错误:"+e+"时间:"+ Utils.getCurrentTime());
                System.out.println("错误:"+e);
            }
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isReadable()){
//                System.out.print((char)in.readByte());
//                System.out.flush();
//            }
//            //ctx.fireChannelRead(msg); //传递下一个handler
//
//        }finally {
//            ReferenceCountUtil.release(msg);
//        }
    }



}
