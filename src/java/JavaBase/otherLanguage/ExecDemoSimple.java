/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.otherLanguage;

import java.io.IOException;

/**
 * 运行外部程序
 */
public class ExecDemoSimple {


  public static void main(String[] args) throws IOException, InterruptedException {
    // exec方法允许我们运行一个外部程序
    Process process = Runtime.getRuntime().exec("F:\\Thunder9\\Program\\ThunderStart.exe");
    process.waitFor();
  }

}
