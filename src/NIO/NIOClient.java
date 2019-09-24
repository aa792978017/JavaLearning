package NIO;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
    private static int flag = 1;
    private static int blockSize = 4096;
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private static ByteBuffer recevieBuffer = ByteBuffer.allocate(blockSize);
    private final static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",7070);

    public static void main(String[] args) throws IOException {

//        打开管道
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //打开选择器和服务端的选择器交互
        Selector selector = Selector.open();
        //把管道注册到选择器上，打开链接
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
//        开始链接，放入要链接的服务器地址
        socketChannel.connect(serverAddress);

        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;
        SocketChannel client;
        String recevieText;
        String sendText;
        int count = 0;
        while (true){
            //获取已经注册到selector上的所有IO事务
           selectionKeys =  selector.selectedKeys();
           iterator = selectionKeys.iterator();
           while (iterator.hasNext()){
               selectionKey = iterator.next();
               if (selectionKey.isConnectable()){
                   System.out.println("client connect");
                   client = (SocketChannel) selectionKey.channel();
//                   如果链接成功
                   if (client.isConnectionPending()){
//                       完成链接
                       client.finishConnect();
                       System.out.println("客户端完成连接操作");
                       sendBuffer.clear();
                       sendBuffer.put("Hello, server".getBytes());
//                       写入缓冲区
                       sendBuffer.flip();
                       client.write(sendBuffer);

                   }
//                   注册读数据IO事务
                   client.register(selector, selectionKey.OP_READ);
               }if (selectionKey.isReadable()){
                   client = (SocketChannel) selectionKey.channel();
                   recevieBuffer.clear();
                   count = client.read(recevieBuffer);
                   if(count > 0){
                       recevieText = new String(recevieBuffer.array(),0,count);
                       System.out.println("客户端接收到服务端数据："+recevieText);
                       client.register(selector,selectionKey.OP_WRITE);
                   }

               }if (selectionKey.isWritable()){
                   sendBuffer.clear();
                   client = (SocketChannel) selectionKey.channel();
                   sendText = "Msg send to server -> " + flag++;
                   sendBuffer.put(sendText.getBytes());
                   sendBuffer.flip();
                   client.write(sendBuffer);
                   System.out.println("客户端发送数据给服务端："+sendText);
                   client.register(selector, selectionKey.OP_READ);
               }
           }
           selectionKeys.clear();
        }

    }
}
