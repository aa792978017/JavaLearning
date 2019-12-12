/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.date;

import java.time.LocalDate;
import java.time.Period;

/**
 * 计算日期差值、加减（LocalDate）
 */
public class DateDiff {

  public static void main(String[] args) {

    LocalDate endofCentury = LocalDate.of(2000, 12, 31);
    LocalDate now = LocalDate.now();
    // 求endofCentury 和 now 的差值
    Period diff = Period.between(endofCentury, now);
    // 从Period中获取差值，单独获取年月日
    System.out.printf("%d year %d months and %d days old",
        diff.getYears(), diff.getMonths(), diff.getDays());


    // 日期或日历的加减
    LocalDate nowDay = LocalDate.now();
    Period pt = Period.ofDays(10);
    LocalDate then = now.plus(pt);
    System.out.println("10 day after is " + then);
  }

}
