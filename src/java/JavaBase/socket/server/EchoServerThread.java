/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程tcp服务器
 */
public class EchoServerThread {
  // 服务器tcp端口号
  private static final int ECHO_PORT = 9999;

  // 开启四个线程来服务
  private static final int NUM_THREADS = 4;

  // 主程序
  public static void main(String[] args) {
    new EchoServerThread(ECHO_PORT, NUM_THREADS);
  }

  private EchoServerThread(int port, int numThreads) {
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException ex) {
      // 如果IO失败，则退出服务器，报告错误
      throw new RuntimeException("Could not create ServerSocket ", ex);
    }
    // 创建一系列线程并启动他们
    for (int i = 0; i < numThreads; i++) {
      new Handler(serverSocket,i).start();
    }
  }


  /**
   * 一个线程子类来处理一个客户端通讯
   */
  class Handler extends Thread {
    // 服务端socket
    private ServerSocket serverSocket;

    private int threadNumber;

    public Handler(ServerSocket serverSocket, int threadNumber) {
      this.serverSocket = serverSocket;
      this.threadNumber = threadNumber;
      setName("Thread " + threadNumber);
    }

    @Override
    public void run() {
      // 等待连接，当调用accept方法时，在服务套接字上同步
      while (true) {
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter printWriter = null;
        try {
          System.out.println(getName() + " waiting");
          // 等待客户端下一次连接
          synchronized (serverSocket) {
            clientSocket = serverSocket.accept();
          }
          System.out.println(getName() + " starting, IP=" + clientSocket.getInetAddress());
          in =
              new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
          String line;
          while ((line = in.readLine()) != null) {
            printWriter.write(line + "\r\n");
            printWriter.flush();
          }
          System.out.println(getName() + "ENDED");
        } catch (IOException ex) {
          System.err.println(ex.toString());
        } finally {
          // 关闭IO资源
          try {
            if (in != null) {
              in.close();
            }
            if (printWriter != null) {
              printWriter.close();
            }
            if (clientSocket != null) {
              clientSocket.close();
            }
          } catch (IOException ex) {
            //资源关闭失败
            System.err.println("I/O resource close exception " + ex.toString());
          }

        }
      }
    }
  }
}

