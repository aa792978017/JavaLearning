/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Files工具类里面有很多读取文件的api
 *
 */
public class Demo3 {

  public static void main(String[] args) throws IOException {
    // 读取文本里面所有字符到字符串里
    String input = new String(Files.readAllBytes(Paths.get("D:\\github repository\\JavaLearning\\src\\java\\JavaBase\\io\\myfile.txt")));
    System.out.println(input);

    try {
      // 重新分配标准流
      String LOGFILENAME = "D:\\github repository\\JavaLearning\\src\\java\\JavaBase\\io\\error.log";
      // 通过System.setErr/In/Out来设置流
      System.setErr(new PrintStream(new FileOutputStream(LOGFILENAME)));
      System.out.println("Please look for errors in " + LOGFILENAME);
      int[] arr = new int[2];
      arr[10] = 1;
    } catch (ArrayIndexOutOfBoundsException ex) {
      // System.setErr(System.out);
      System.out.println("exception");
      System.err.println("heihei");

    }

  }

}
