/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.otherLanguage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import sun.security.ssl.Debug;

/**
 * 运行一个程序并捕捉其输出
 */
public class ExecDemoDir {
  /** 要运行的程序 */
  public static final String PROGRAM = "cmd /c start dir";

  static volatile boolean done = false;

  public static void main(String[] args) throws IOException {
    final Process p;
    BufferedReader is ;
    String line;
    p = Runtime.getRuntime().exec(PROGRAM);
    Debug.println("exec", "In Main after exec");

    Thread waiter = new Thread() {
      public void run() {
        try {
          p.waitFor();
        } catch (InterruptedException ex) {
          return;
        }
        System.out.println("Program terminated!");
        done = true;
      }
    };

    waiter.start();
    // 获取输入流
    is = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
    System.out.println("-----------------");
    while (!done && ((line = is.readLine()) != null)){
      System.out.println(line);
    }
    System.out.println("-----------------");
    Debug.println("exec", "In Main after EOF");
    return;
  }
}
