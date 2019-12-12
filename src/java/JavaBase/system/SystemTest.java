/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.system;

/**
 * 测试System的API
 */
public class SystemTest {

  public static void main(String[] args) {
    // 返回一个map类型的值，装了所有环境变量
    System.out.println(System.getenv());
    // 返回某个环境变量
    System.out.println(System.getenv("PATH"));

    // 从系统中获取信息(虚拟机，操作系统，jdk等等)
    // 获取所有信息
    System.out.println(System.getProperties());
    // 通过 java -D"pencil_color=Deep Green"运行
    System.out.println(System.getProperty("pencil_color"));
    // 获取jdk版本
    System.out.println(System.getProperty("java.specification.version"));
    // 判断某个特定的类是否存在
    try {
      Class.forName("javafx.swing.*");

    } catch (ClassNotFoundException ex) {
      System.out.println("找不到该类 ： " + ex.toString());
    }

  }

}
