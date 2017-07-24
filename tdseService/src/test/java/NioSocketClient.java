import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/6/28.
 */
public class NioSocketClient
{

    private final static Logger log = Logger.getLogger(NioSocketClient.class.getName());
    private final static String START = "START";
    private InetSocketAddress inetSocketAddress;

    /* 发送数据缓冲区 */
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    public NioSocketClient(int port){
        try{
            inetSocketAddress = new InetSocketAddress("localhost", port);
            init();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
    private void init(){
        try {
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            socketChannel.configureBlocking(false);
            sendFile(socketChannel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFile(SocketChannel client) {
        FileInputStream fis = null;
        FileChannel channel = null;
        try {
            File file = new File("E:\\Arvin\\JAVA_PROJECT\\ProductWarehouse\\八大主题\\测试安全\\测试游戏.unitypackage");
            fis = new FileInputStream(file);
            channel = fis.getChannel();
            int i = 1;
            int sum = 0;
            int len = 0;

            //写文件头部
            String file_name = file.getName();
            Long file_length = file.length();
            writeFileHead(client, START);
            writeFileHead(client, file_name);
            writeFileHead(client, String.valueOf(file_length));

            //写文件内容
            while((len = channel.read(sendBuffer)) != -1) {
                sendBuffer.flip();
                int send = client.write(sendBuffer);

                log.info("已发送文件总字节数" + (sum += len));
                log.info("i发送-->" + (i++) + " len:" + len + " send:" + send);
                // 考虑到服务器缓冲区满的情况
                while(send == 0){
                    Thread.sleep(10);
                    send = client.write(sendBuffer);
                    log.info("i重新-->" + i + " len:" + len + " send:" + send);
                }
                sendBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生错误"+e);
        } finally {
            try {
                channel.close();
                fis.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @category 写文件头部
     * @param channel
     * @param data
     * @throws IOException
     */
    public void writeFileHead(SocketChannel channel, String data) throws IOException {
        byte[] d_content = data.getBytes();
        byte[] d_len = i2b(d_content.length);

        sendBuffer.put(d_len);
        sendBuffer.put(d_content);
    }

    private byte[] i2b(int i) {
        //4个字节
        return new byte[] {
                (byte) ((i >> 24) & 0xFF),
                (byte) ((i >> 16) & 0xFF),
                (byte) ((i >> 8) & 0xFF),
                (byte) (i & 0xFF),
        };
    }

    public static void main(String[] args){
        new NioSocketClient(12345);
    }
}
