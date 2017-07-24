import com.tdse.mx.util.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.*;

/**
 * Created by Administrator on 2017/6/28.
 */
public class FileReceiveHandler extends  ChannelInboundHandlerAdapter
{
    /**
     *  初始化
     */
    public FileReceiveHandler() {
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
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
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
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        super.channelInactive(ctx);
    }

     public int number=0;
    /**
     * 通道读
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
//        try {
//            ByteBuf buf = (ByteBuf) msg;
//            byte[] req = new byte[buf.readableBytes()];
//            buf.readBytes(req);
//            String body = new String(req, "UTF-8");
//            System.out.println("服务器接收到消息：" + body);
//
//            String s = JsonUtils.getValue(body,"requestType").toString();
//
//            if (s.trim().equals("Init"))
//            {
//
//                File file = new File("E:\\Arvin\\JAVA_PROJECT\\ProductWarehouse\\八大主题\\测试安全\\测试游戏.unitypackage");
//                FileInputStream fis = new FileInputStream(file);
//                byte[] bytes = new byte[1024];
//                long i =file.length();
//                while (fis.read(bytes) != -1)
//                {
//                    ByteBuf resp = Unpooled.copiedBuffer(head(bytes,i));
//                    ctx.write(resp);
//                }
//            }else if (s.trim().equals("Start"))
//            {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
            ByteBuf buf = (ByteBuf) msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req, "UTF-8");
            System.out.println("服务器接收到消息：" + body);
            String s = JsonUtils.getValue(body,"requestType").toString();
            if (s.trim().equals("Init"))
            {
                File file = new File("E:\\Arvin\\JAVA_PROJECT\\ProductWarehouse\\八大主题\\测试安全\\测试游戏.unitypackage");
                try {
                    RandomAccessFile r =new RandomAccessFile(file,"r");
                    r.skipBytes(number);
                    byte[] bytes = new byte[1024];
                    r.read(bytes);
                    long i =file.length();
                    ByteBuf resp = Unpooled.copiedBuffer(head(bytes,i));
                    ctx.write(resp);
                    number+=bytes.length;
                    System.out.println("当前进度"+number);
                    System.out.println("最后进度"+i);
                }catch (Exception e){

                }finally
                {

                }

            }else if (s.trim().equals("Start"))
            {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static byte[] head(byte[] data,long length)
    {
        String name ="测试游戏.unitypackage";
        String datalength=""+length;
        String datalengths =""+data.length;

        byte [] bytes= new byte[0];
        byte [] bytes1=new byte[0];
        byte [] bytes2 =new byte[0];
        try {
            bytes = name.getBytes("UTF-8");
            bytes1=datalength.getBytes("UTF-8");
            bytes2=datalengths.getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //System.out.println("名字长度:"+bytes.length+"||文件总长度:"+bytes1.length+"||当前数据长度:"+bytes2.length);

        byte[] bytes3 =new byte[bytes.length+bytes1.length+bytes2.length+data.length];
        System.arraycopy(bytes,0,bytes3,0,bytes.length);
        System.arraycopy(bytes1,0,bytes3,25,bytes1.length);
        System.arraycopy(bytes2,0,bytes3,33,bytes2.length);
        System.arraycopy(data,0,bytes3,37,data.length);
        //System.out.println("最后数据长度"+bytes3.length);
        return  bytes3;
    }
}
