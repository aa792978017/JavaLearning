/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 轻量级Web服务客户端（浏览器）
 */
public class RestClientFreeGeoIp {

  public static void main(String[] args) throws Exception {
    // 打开url
    URLConnection connection = new URL(
        "http://www.baidu.com").openConnection();
    // 读取返回值
    try (BufferedReader is = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      String line;
      while ((line = is.readLine()) != null) {
        System.out.println(line);
      }
    }
  }
}
