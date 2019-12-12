/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.number;

import java.text.NumberFormat;

/**
 * 二进制、八进制、十进制和十六进制之间的转换
 */
public class IntegerBinOctHexEtc {

  public static void main(String[] args) {

    String input  = "101010";
    // radix 至多少进制
    for (int radix : new int[] {2, 8,10, 16, 36}) {
      // 二进制转八、十、十六进制
      System.out.println(input + "in base " + radix + " is " +
          Integer.valueOf(input, radix) + ";");

      // 十进制转其他进制
      int i = 42;
      System.out.println(i + " formatted in base " + radix + " is "
          + Integer.toString(i ,radix));
    }

    // 检查字符串是否为某类型的数字
    String aNumber = "123";
    double result;
    try {
      result = Double.parseDouble(aNumber);
    } catch (NumberFormatException ex) {
      System.out.println("不是 double 型");
    }

    // 格式化数字，如：001.00
    NumberFormat form = NumberFormat.getInstance();
    // 格式化为 999.99[99]
    form.setMinimumIntegerDigits(3);
    form.setMinimumFractionDigits(2);
    form.setMaximumFractionDigits(4);
    double[] data = {0, 1, 22d/7, 100.234567};
    // 开始格式化data
    for (int i = 0; i < data.length; i++) {
      System.out.println(data[i] + "\tformats as " + form.format(data[i]));
    }


  }
}
