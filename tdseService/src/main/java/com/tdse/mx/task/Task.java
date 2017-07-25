package com.tdse.mx.task;

import com.tdse.mx.event.SocketEventManager;
import com.tdse.mx.server.FileDemo;
import com.tdse.mx.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/25.
 */
public class Task
{
    public enum State
    {
        START,
        PAUSE,
        RUNNING,
        FINISHED;
        public static  State GetType(String posType){
            return valueOf(posType.toUpperCase());
        }
    }

    private State state= State.START;

    private ChannelHandlerContext ctx;
    private Object  msg;


    public State getState()
    {
       return state;
    }

    public void setState(State state)
    {
       this.state=state;
    }

    private void Run()
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
    }
}
