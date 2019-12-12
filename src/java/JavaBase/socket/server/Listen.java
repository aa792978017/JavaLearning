/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 打开服务端套接字，监听连接
 */
public class Listen {

  public static final short PORT = 9999;

  public static void main(String[] args) {
    ServerSocket sock;
    Socket clientSock;
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
