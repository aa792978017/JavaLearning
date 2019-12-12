/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 添加解析、和客户端等待连接数功能
 */
public class ListenInside {

  /**
   * 网络接口名称
   */
  public static final String INSIDE_HOST = "xxx-inside";

  /**
   * 服务器的tcp端口号
   */
  public static final short PORT = 9999;

  /**
   * 允许排队的客户端的数量（在大量客户端同时访问，服务器太忙时，可排队的客户端数量）
   */
  public static final int BACKLOG = 10;

  public static void main(String[] args) {
    ServerSocket sock;
    Socket clientSock;
    try {
      // 操作系统会在系统的网络配置文件里面找到INSIDE_HOST对应的主机名（ip）
      sock = new ServerSocket(PORT, BACKLOG, InetAddress.getByName(INSIDE_HOST));
      // accpet()会一直阻塞，知道有客户端连接
      while ((clientSock = sock.accept()) != null) {
        // 有客户端连接了，开始处理客户端连接
        process(clientSock);
      }
    } catch (Exception ex) {
      System.err.println("服务端套接字异常： " + ex.toString());
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
