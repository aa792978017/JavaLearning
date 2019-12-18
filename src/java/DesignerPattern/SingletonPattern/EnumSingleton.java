/*
 * Copyright 2019-2022 the original author or authors.
 */

package DesignerPattern.SingletonPattern;

/**
 * 枚举式单例模式(枚举类型在静态代码块实例化，属于饿汉式)
 * 可以解决线程安全以及序列化导致单例不唯一的问题（使用JDK枚举语法的特殊性、effective java也提倡这种方式）
 */
public enum  EnumSingleton {
  INSTANCE;

  private Object data;

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
  public static EnumSingleton getInstance() {
    return INSTANCE;
  }
}
