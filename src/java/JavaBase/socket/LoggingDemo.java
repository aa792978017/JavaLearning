/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j-日志记录(如何使用：1.从LoggerFactory获取Logger对象； 2.选择合适的级别打印信息)
 */
public class LoggingDemo {
  // 从LoggerFactory获取Logger
  final static Logger theLogger = LoggerFactory.getLogger(LoggingDemo.class);

  public static void main(String[] args) {
     Object o = new Object();
     // 底层次的信息消息
     theLogger.info("I created an object: " + o.toString());
     // 加上{}(占位符)， 参数按顺序填入指定位置
     theLogger.info("I created an  {} object: ", o.toString());
     // 详细调试
     theLogger.debug("I created an object: " + o.toString());
    theLogger.info("I created an  {} object: {} ", o.toString(), o.toString());
     // 可能的错误
     theLogger.warn("I created an object: " + o.toString());
     // 严重的错误
     theLogger.error("I created an object: " + o.toString());

  }
}
