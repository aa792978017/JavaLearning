/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo1 {

  public static void main(String[] args) throws IOException {
    // 读取一个字节
    int b = 0;
     try {
       b = System.in.read();
       System.out.println("Read this data:" + b);
     } catch ( Exception ex) {
       System.out.println(ex.toString());
     }

     // 一直监听系统输入，只要输入后回车，就进入标准输入
     try {
       BufferedReader is =
           new BufferedReader(new InputStreamReader(System.in));
       String inputLine;
       while ((inputLine = is.readLine()) != null) {
         System.out.println(inputLine);
       }
     } catch (IOException ex) {
       System.out.println(ex.toString());
     }


  }

}
