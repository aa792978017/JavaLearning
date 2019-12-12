/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 查找网络接口
 */
public class NetworkInterfaceDemo {

  public static void main(String[] args) throws IOException {
    Enumeration list = NetworkInterface.getNetworkInterfaces();
    while (list.hasMoreElements()) {
      // 得到一个NetworkInterface
      NetworkInterface iface = (NetworkInterface)list.nextElement();
      // 打印它的名字
      System.out.println(iface.getDisplayName());
      Enumeration addrs = iface.getInetAddresses();
      // 它的地址
      while (addrs.hasMoreElements()) {
        InetAddress addr = (InetAddress) addrs.nextElement();
        System.out.println(addr);
      }
    }
    System.out.println("----------------------------------------------------");
    // 试图获得一个给定的本地(本机)地址接口
    InetAddress destAddr = InetAddress.getByName("localhost");
    try {
      NetworkInterface dest = NetworkInterface.getByInetAddress(destAddr);
      System.out.println("Address for " + destAddr + " is " +dest);
    } catch (SocketException ex) {
      System.err.println("Couldn't get address for " + destAddr);
    }
  }
}
