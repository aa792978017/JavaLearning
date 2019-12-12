/*
 * Copyright 2019-2022 the original author or authors.
 */
package JavaBase.jar;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1、在 目录”jar“下执行 ”javac 编译成class“（注意java文件里面的包名）
 * 2、执行“jar cvmf manifest.stub Listen.jar Listen.class" 命令生成jar包
 * 3、查看jar包里面的MANIFEST.MF中是否含”Main-Class： Listen“，是否指定主类
 * manifest样单用于列出Jar内容和他们的属性：
 *  属性以： name：value 的形式
 *  其中MainProgram头用于指定主程序
 * 因此这里manifest.stub中有：Main-Class：Listen
 */
public class Listen {

  public static final short PORT = 9999;

  public static void main(String[] args) {
    ServerSocket sock;
    Socket clientSock;
    System.out.println("Listen main fuction running");
    try {
      sock = new ServerSocket(PORT);
      // accpet()会一直阻塞，知道有客户端连接
      while ((clientSock = sock.accept()) != null) {
        // 有客户端连接了，开始处理客户端连接
        process(clientSock);
      }
    } catch (Exception ex) {
      System.out.println("服务端套接字异常： " + ex.toString());
    }

  }

  /**
   * 与一个客户端交流
   * @param clientSock 客户端套接字
   */
  private static void process(Socket clientSock) throws IOException {
    System.out.println("Accept from client " + clientSock.getInetAddress());
    // 进行交流
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSock.getOutputStream()));
    out.write("Hi I am server");
    out.flush();
    // 交流结束，关闭客户端的套接字
    clientSock.close();

  }

}
