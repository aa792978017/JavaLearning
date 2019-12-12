/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class InetAddressDemo {

  public static void main(String[] args) throws IOException {
    String hostName = "www.darwinsys.com";
    String ipNumber = "8.8.8.8"; // 谷歌的dns服务器
    // 通过主机名得到InetAddress（查找主机）
    System.out.println(hostName + "'s address is " + InetAddress.getByName(hostName));

    // 通过地址寻找一个主机名
    System.out.println(ipNumber + "'s name is " +
        InetAddress.getByName(ipNumber).getHostAddress());

    // 查询我的本地主机地址
    final InetAddress localhost = InetAddress.getLocalHost();
    System.out.println("My localhost address is " + localhost);

    // 从打开的套接字得到一个InetAddress
    String someServerName = "www.goole.com";
    Socket theSocket = new Socket(someServerName, 80);
    InetAddress remote = theSocket.getInetAddress();
    System.out.printf("The InetAddress for %s is %s%n", someServerName, remote);

    // 通过InetAddress构造Socket
    Socket antherSocket = new Socket(remote, 80);
    System.out.println("Another soctet : " + antherSocket);

  }

}
