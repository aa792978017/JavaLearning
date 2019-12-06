/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.socket.client;

import java.io.IOException;
import java.net.Socket;

/**
 * 使用tcp/ip连接一个服务器
 */
public class ConnectSimple {

  public static void main(String[] args) throws IOException {
    try(Socket sock = new Socket("169.254.148.182", 80)){
      // 这里如果没有报错，就算是连接成功了
      System.out.println(" *** Connected OK *** ");

    }
  }
}
