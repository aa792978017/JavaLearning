/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 给服务器发送消息，然后接受服务器的响应
 */
public class EchoClientOneLine {
  /**
   * 在网络上发送的消息
   */
  public String message = "hello across the net";

  public static void main(String[] args) {
    if (args.length == 0)
      new EchoClientOneLine().converse("www.darwinsys.com");
    else
      new EchoClientOneLine().converse(args[0]);
  }

  /**
   * 保持一个会话
   * @param hostName 主机名
   */
  private void converse(String hostName) {
    try {
      // echo 服务器
      Socket socket = new Socket(hostName, 7);
      BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter os = new PrintWriter(socket.getOutputStream(), true);
      // 因为println在某些平台仅仅追加一个\r，所有定义回车为”\r\n“
      os.print(message + "\r\n");
      os.flush();
      String reply = is.readLine();
      System.out.println("Sent \"" + message + "\"");
      System.out.println("Got \"" + reply + "\"");
    }catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }

}
