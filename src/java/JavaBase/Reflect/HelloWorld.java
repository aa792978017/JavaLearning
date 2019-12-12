/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Reflect;

/**
 * 测试helloworld.java 运行时都加载了哪些类
 * java -verbose HelloJava | more
 */
public class HelloWorld {

  public static void main(String[] args) {
    System.out.println("Hello world!");
  }

}
