/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 轻量级Web服务客户端
 */
public class RestClientFreeGeoIp {

  public static void main(String[] args) throws Exception {
    URLConnection connection = new URL(
        "http://www.baidu.com").openConnection();

    try (BufferedReader is = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      String line;
      while ((line = is.readLine()) != null) {
        System.out.println(line);
      }
    }
  }
}
