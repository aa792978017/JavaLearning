/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 输出服务器日期和时间
 */
public class DaytimeText {
  public static final short TIME_PORT = 13;

  public static void main(String[] args) {
    String hostName;
    if (args.length == 0)
      hostName = "www.darwinsys.com";
    else
      hostName = args[0];

    try {
      //
      Socket sock = new Socket(hostName, TIME_PORT);
      //
      BufferedReader is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      //
      String remoteTime = is.readLine();
      System.out.println("Time on " + hostName + " is " + remoteTime);
    }catch (Exception ex) {
      System.out.println(ex);
    }
  }
}
