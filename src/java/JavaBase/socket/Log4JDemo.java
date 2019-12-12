/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * log4j 日志记录
 * log4j必须有一个配置文件(默认名为：log4j.properties),否则会编译出错（maven命令的时候）
 *
 *
 */
public class Log4JDemo {

  public static void main(String[] args) {
    PropertyConfigurator.configure("D:\\github repository\\JavaLearning\\log4j.properties");
    // 配置标识符可以为包名、类命
//    Logger myLogger = Logger.getLogger("Java.socket");
    Logger myLogger = Logger.getLogger(Log4JDemo.class);
//    Logger myLogger = Logger.getLogger(Log4JDemo.class);
    Object o = new Object();
    myLogger.info("I create an object:" + o);
    // 不能使用占位符的方式
//    myLogger.info("I create an object: {}" , o);
  }
}
