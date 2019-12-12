/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式化
 */
public class DateFormatter {

  public static void main(String[] args) {
    // 这里是用斜杠，不是破折号
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    System.out.println(df.format(LocalDateTime.now()));

    // 使用同一个格式化对象将字符串解析为日期
    System.out.println(LocalDate.parse("2014/04/01", df));

    // 格式化日期时间，不含时区信息, 4个M表示中文，3个M表示（Jan，Feb月份）
    DateTimeFormatter nTZ = DateTimeFormatter.ofPattern("d MMMM,  yyyy h:mm a");
    System.out.println(ZonedDateTime.now().format(nTZ));

    // 字符串解析为日期
    String date = "1914-11-11";
    LocalDate aLD = LocalDate.parse(date);
    System.out.println("Date: " + aLD);

    String dateTime = "1914-11-11T11:11";
    LocalDateTime aLDT = LocalDateTime.parse(dateTime);
    System.out.println("Date/Time: " + aLDT);

  }
}
