package JavaBase.socket.server;/*
 * Copyright 2019-2022 the original author or authors.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * 使用SSL协议
 * 注意：这里需要设置一个服务器证书，否则会出现javax.net.ssl.SSLHandshakeException异常
 * 提示没有共同的密码套件（因为没有它，JSSE不能使用任何内置密码）
 * java -Djavax.net.ssl.keyStore=D:\github repository\JavaLearning\src\java\JavaBase\jar\.keystore -Djavax.net.ssl.keyStorePassword=792978017 JSSEWebServer
 */
public class JSSEWebServer extends WebServer {
  private static final int HTTPS = 8433;

  public static void main(String[] args) throws IOException {
    if (System.getProperty("javax.net.ssl.keyStore") == null) {
      System.err.println(
          "You must pass in a keyStore via -D; see the documentation");
      System.exit(1);
    }
    System.out.println("JSSE Server starting...");
    JSSEWebServer jsseWebServer = new JSSEWebServer();
    jsseWebServer.runserver(HTTPS); // 从不返回
  }

  /**
   * 使用JSSE得到一个HTTPS服务器套接字
   * 如果SecurityProvider不能实例化，则抛出ClassNotFoundException异常
   * @param port 监听的端口号
   * @return HTTPS服务器套接字
   * @throws IOException
   */
  @Override
  protected ServerSocket getServerSocket(int port) throws IOException {
    SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    return ssf.createServerSocket(port);
  }
}


