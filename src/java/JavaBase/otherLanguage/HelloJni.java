/*
 * Copyright 2019-2022 the original author or authors.
 */



/**
 * 使用JNI调用本机方法（c方法）
 * 1、编写Java代码（包含native方法）
 * 2、编译此Java代码，（javac HelloJni.java）
 * 3、使用javah 创建一个.h文件（javah HelloJni）
 * 4、设置各种环境变量（include环境，C库的和Java库的）
 * 5、利用.h文件的方法名和参数，编写C代码
 * 6、然后使用命令：cl HelloJni.c -Fehello.dll -MD -LD生成hello.dll文件(该文件在java文件里面使用System.loadLibary(hello)读取)
 * cl HelloJni.c -Fehello.dll -MD -LD
 */
public class HelloJni {

  int myNumber = 42;

  // 声明本机类
  public native void displayHelloJni();

  public static void main(String[] args) {

    System.out.println("HelloJni starting; args.length=" + args.length + "...");
    for (int i = 0; i < args.length; i++) {
      System.out.println("args["+i+"]="+args[i]);
    }
    HelloJni hw = new HelloJni();
    // 调用本机函数
    hw.displayHelloJni();
    System.out.println("Back in Java, \"myNumber\" now " + hw.myNumber);
  }

  /** 当加载类文件时，执行一次静态代码块， 这里加载hello.dll*/
  static {
    System.loadLibrary("hello");
  }
}
