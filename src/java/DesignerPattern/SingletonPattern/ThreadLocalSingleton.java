/*
 * Copyright 2019-2022 the original author or authors.
 */

package DesignerPattern.SingletonPattern;

/**
 * 线程单例
 * 无法保证只创建一个，但是确保线程中使用的是同一个
 */
public class ThreadLocalSingleton {
  private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance
      = new ThreadLocal<ThreadLocalSingleton>(){
    @Override
    protected ThreadLocalSingleton initialValue() {
      return new ThreadLocalSingleton();
    }
  };


  private static ThreadLocalSingleton getInstance() {
    return threadLocalInstance.get();
  }
}
