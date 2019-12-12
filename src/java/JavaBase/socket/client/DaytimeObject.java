/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.client;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

/**
 * 读写串行化数据，读取服务器传过来的对象
 */
public class DaytimeObject {
  public static final short TIME_PORT = 13;

  public static void main(String[] args) {
    String hostName;
    if (args.length == 0)
      hostName = "www.darwinsys.com";
    else
      hostName = args[0];

    try {
      Socket socket = new Socket(hostName, TIME_PORT);
      ObjectInputStream is = new ObjectInputStream(
          new BufferedInputStream(socket.getInputStream()));
      // 读取和验证对象
      Object o = is.readObject();
      if (o == null) {
        System.out.println("Read null from server");
      } else if ((o instanceof Date)) {
        // 有效的，因此转换为Date类型，并打印
        Date date = (Date) o;
        System.out.println("Server host is " + hostName);
        System.out.println("Time there is " + date.toString());
      } else {
        throw new IllegalArgumentException("Wanted Date, got" + o);
      }
    }catch (ClassNotFoundException ex) {
      System.out.println("Wanted Date, got INVALID CLASS (" + ex.toString() + ")");
    }catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}
