/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个Echo服务器，可以不断接收一个客户端对其不断发送的消息
 */
public class EchoServer {

  /**
   * 服务端套接字
   */
  private ServerSocket serverSocket;

  /**
   * 默认使用的端口号
   */
  private final static int ECHO_PORT = 9999;

  /**
   * 控制调试标志
   */
  private boolean debug = true;

  /**
   * 主函数，构建和运行该服务器
   * @param args 启动传入的参数，这里传入的是端口号
   */
  public static void main(String[] args) {
    int port = ECHO_PORT;
    if (args.length == 1) {
      try {
        port = Integer.parseInt(args[0]);
      } catch (NumberFormatException ex) {
        System.err.println("Usage: EchoServer [port#]");
        System.exit(1);
      }
    }
    new EchoServer(port).handle();
  }

  public EchoServer(int port) {
    try {
      // 创建服务端套接字
      serverSocket = new ServerSocket(port);
    } catch (IOException ex) {
      System.out.println("I/O error in setup");
      System.out.println("Detail exception is: " + ex.toString());
      // 退出程序
      System.exit(1);
    }
  }

  private void handleByThreads() {}


  /**
   * 处理客户端连接(单线程)
   */
  private void handle() {
    Socket clientSocket = null;
    BufferedReader reader = null;
    BufferedWriter writer = null;
    while (true) {
      try {
        System.out.println("Waiting for client...");
        // 服务端监听连接过来的客户端
        clientSocket = serverSocket.accept();
        System.out.println("Accepted from " + clientSocket.getInetAddress().getHostName());
        // 获取客户端的输入输出流
        reader = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream(),"8859_1"));
        writer = new BufferedWriter(
            new OutputStreamWriter(clientSocket.getOutputStream(), "8859_1"));
        String echoLine;
        while ((echoLine = reader.readLine()) != null) {
          System.out.println("Read " + echoLine);
          // 打印出来
          writer.write(echoLine + "\r\n");
          writer.flush();
          System.out.println("Wrote " + echoLine);
        }
        System.out.println("All done");
      } catch (IOException ex) {
        System.err.println(ex.toString());
      } finally {
        // 释放打开的io资源
        try {
          if (reader != null) {
            reader.close();
          }
          if (writer != null) {
            writer.close();
          }
          if (clientSocket != null) {
            clientSocket.close();
          }
        } catch (IOException ex) {
          // 这些异常情况不一定会发生，但可能是另一端提前关闭或关闭前已满但没有检查等
          System.err.println("IO Error in close");
        }
      }
    }
  }

}
