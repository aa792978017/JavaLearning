package Java.NIO;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private int flag = 1;
    private int blockSize = 4096;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private ByteBuffer recevieBuffer = ByteBuffer.allocate(blockSize);
    private Selector selector;

    public NIOServer(int port)throws Exception{
//        打开一个服务器管道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设定为非阻塞，这样才是NIO
        serverSocketChannel.configureBlocking(false);
//        通过管道获取socket
        ServerSocket serverSocket = serverSocketChannel.socket();
//        绑定一个端口号/IP
        serverSocket.bind(new InetSocketAddress(port));
//        打开选择器
        selector = Selector.open();
//        完成初始化，服务器开始注册接受链接的IO事务
//        给Selector注册一个事务
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start ->" + port);
    }

    /**
     * 服务器开始监听
     * @throws Exception
     */
    public void listen()throws Exception{

        while (true){
            int j = 1;            //获取注册在Selector上的事务，查看是否有可以执行的（即完成了IO的）
            selector.select();
            System.out.println("111");
            //使用迭代器轮询每一个事务
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                //获取注册的IO事务
                SelectionKey selectionKey = iterator.next();
//                业务逻辑
                handleKey(selectionKey);
                //获取以后就删除事务
                iterator.remove();
//
            }
        }
    }

    public void handleKey(SelectionKey selectionKey)throws Exception{
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String reviceText;
        String sendText;
        int count = 0;
        System.out.println("1");
        if (selectionKey.isAcceptable()){
//            此时获取到的管道是服务端的
            server = (ServerSocketChannel) selectionKey.channel();
            client = server.accept();
//            不让客户端阻塞
            client.configureBlocking(false);
//            客户端注册读取
            client.register(selector, selectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            client = (SocketChannel) selectionKey.channel();
            count = client.read(recevieBuffer);
            if (count > 0){
                reviceText  = new String(recevieBuffer.array(), 0, count);
                System.out.println("服务端接收到客户端的消息：" + reviceText);
                client.register(selector, selectionKey.OP_WRITE);
            }
        }else if (selectionKey.isWritable()){
            sendBuffer.clear();
            client = (SocketChannel) selectionKey.channel();
            sendText = "msg send to client:" + flag++;
            sendBuffer.put(sendText.getBytes());
            sendBuffer.flip();
            client.write(sendBuffer);
            System.out.println("服务端发送数据给客户端："+sendText);
//            client.register(selector, selectionKey.OP_READ)
        }
    }




    public static void main(String[] args) throws Exception {
        int port = 7070;
        NIOServer nioServer = new NIOServer(port);
        nioServer.listen();
    }
}
