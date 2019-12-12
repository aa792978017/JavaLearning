/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.date;

import com.rabbitmq.tools.json.JSONUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 测试新的日期api，获取当前时间
 * 都是线程安全的，都是final的
 */
public class CurrentDateTime {

  public static void main(String[] args) {
    LocalDate dNow = LocalDate.now();
    System.out.println(dNow);
    LocalTime dTime = LocalTime.now();
    System.out.println(dTime);
    LocalDateTime dateTime = LocalDateTime.now();
    System.out.println(dateTime);
  }
}
