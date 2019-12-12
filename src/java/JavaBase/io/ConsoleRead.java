/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.io;

import java.io.Console;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Formatter;

/**
 * 该类测试需要在cmd上执行
 * 控制台类用于直接从控制终端读取数据
 * 在终端窗口运行一个程序时，默认情况控制台和标准输入都连接到终端。
 * 大多数操作系统中，标准输入通过管道或者重定向发送改变，Console类可以邦族我们绕过所有间接途径，直接读取用户输入
 */
public class ConsoleRead {

  public static void main(String[] args) {
    // 打开终端，输出What is your name？ ，然后读取你后续输入的一行，传入name
    String name = System.console().readLine("What is your name?");
    System.out.println("Hello, " + name.toUpperCase());


    // 隐藏输入
    Console console;
    if ((console = System.console()) != null) {
      char[] passwd = null;
      try {
        passwd = console.readPassword();
        System.out.println("Your password was: " + new String(passwd));
      } finally {
         if (passwd != null) {
           Arrays.fill(passwd, ' ');
         }
      }
    } else {
      throw new RuntimeException("No console, can't get password");
    }

    // 这两个等价，System.out会接入输出流
    System.out.println();

    PrintWriter pw = new PrintWriter(System.out);
    pw.println();

    // 字符串格式化输出
    Object result = String.format("%d year %d month", 2019, 12);
    System.out.println(result);
    Formatter fmtr = new Formatter();
    result = fmtr.format("%d year %d month", 2020, 1);
    System.out.println(result);

  }

}
