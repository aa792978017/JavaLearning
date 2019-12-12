/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 使用tcp/ip连接一个服务器
 */
public class ConnectSimple {

  public static final short PORT = 80;

  public static final String HOST_NAME = "127.0.0.1";

  public static void main(String[] args) {
    try (Socket sock = new Socket(HOST_NAME, PORT)) {
      // 这里如果没有报错，就算是连接成功了
      System.out.println(" *** Connected OK *** ");
      // 读取服务器传来的消息
      BufferedReader is;
      BufferedWriter out;

      out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
      out.write("Hi I am ClientA");
      out.flush();
//      Thread.sleep(5000);
//      is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//      String response;
//      while ((response = is.readLine()) != null) {
//        System.out.println("message from server: " + response);
//        break;
//      }

    } catch (Exception ex) {
      System.out.println("Exception: " + ex.toString());
    }
  }
}
