/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 打开文件
 */
public class Demo2 {

  public static void main(String[] args) throws IOException {
    // 打开文件
    BufferedReader is = new BufferedReader(new FileReader(
        "D:\\github repository\\JavaLearning\\src\\java\\JavaBase\\io\\myfile.txt"));
    BufferedOutputStream bytesOut = new BufferedOutputStream(
        new FileOutputStream("D:\\github repository\\JavaLearning\\src\\java\\JavaBase\\io\\bytes.dat"));
    bytesOut.close();
  }
}
