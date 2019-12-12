/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单的http服务器
 */
public class WebServer {
  protected static final int PORT = 80;

  protected static final String CRLF = "\r\n";

  protected ServerSocket serverSocket;

  protected static final String VIEW_SOURCE_URL =
      "https://github.com/IanDarwin/javasrc/tree/master/src/main/java/network";

  // 只创建一个服务器，并调用它的runserver函数
  public static void main(String[] args) throws IOException {
    System.out.println("DarwinSys JavaWeb starting...");
    WebServer webServer = new WebServer();
    webServer.runserver(PORT);
  }

  /**
   * 得到实际的ServerSocket
   * 延迟构造，可以结合工厂方法
   * @param port 监听的端口号
   * @return 返回新的ServerSocket
   * @throws IOException
   */

  protected ServerSocket getServerSocket(int port) throws IOException {
    return new ServerSocket(port);
  }

  // runserver接受客户端连接并传给处理程序
  protected void runserver(int port) throws IOException {
    serverSocket = getServerSocket(port);
    while (true) {
      try {
        Socket client = serverSocket.accept();
        Handler(client);
      } catch (IOException ex) {
        System.err.println(ex.toString());
        return;
      }
    }
  }

  /**
   * 处理一个客户端会话
   * @param clientSocket 客户端socket连接
   * @throws Exception
   */
  protected void Handler(Socket clientSocket) {
    BufferedReader is; // 浏览器的输入流
    PrintWriter os; // 浏览器输出流
    String request; // 哪个浏览器发送的请求
    try {
      String from = clientSocket.getInetAddress().toString();
      System.out.println("Accepted connection from " + from);
      is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      // 读取浏览器的请求，一般来说，是解析http协议，获取它的头和body，然后做相应处理
      request = is.readLine();
      System.out.println("Request: " + request);

      os = new PrintWriter(clientSocket.getOutputStream(), true);
      os.print("HTTP/1.0 200 Here is you data" + CRLF);
      os.print("Content-type: text/html" + CRLF);
      os.print("Server-name: DarwinSys Null Java WebServer 0" + CRLF);
      String reply1 = "<html><head>"+
          "<title>Wrong System Reached</title></head>\n" +
          "<h1>Welcome, ";
      String reply2 = ", but...</h1>\n" +
          "<p> You have reached a desktop machine " +
          "that does not run a real Web server.</p>\n" +
          "<p>Please pick another system!</p>\n" +
          "<p> Or view <a href=\"" + VIEW_SOURCE_URL + "\">" +
          "the WebServer0 source on github</a>.</p>\n" +
          "<hr/><em>Java-based WebServer0</em><hr/>\n" +
          "</html>\n";
      os.print("Content-length: " +
          (reply1.length() + from.length() + reply2.length()) + CRLF);
      os.print(CRLF);
      String response = reply1 + from + reply2 + CRLF;
      os.print(response);
      os.flush();
      clientSocket.close();
    } catch (IOException ex) {
      System.out.println("IOException:" + ex.toString());
    }
    return;
  }

}
