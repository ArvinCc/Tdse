import java.net.InetSocketAddress;
import java.util.logging.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2017/6/28.
 */
public class DemoServer
{
    private static final Logger log = Logger.getLogger(DemoServer.class.getName());
    private InetSocketAddress inetSocketAddress;

    public DemoServer(int port) {
        this.inetSocketAddress = new InetSocketAddress(port);
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.childHandler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    //ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new FileReceiveHandler());

                }
            });

            log.info("server listen on " + inetSocketAddress.getPort() + ".");

            ChannelFuture f = b.bind(inetSocketAddress).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        DemoServer ns = new DemoServer(8080);
        ns.start();
    }
}
