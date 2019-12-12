/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.javadoc;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import javax.swing.JPanel;

/**
 * 在该类的目录瞎，通过 "javadoc -author -version JavadocDemo.java" 生成注释文件
 * 还有很多相关的参数，如-link等
 */
public class JavadocDemo extends JPanel {

  /**
   * 构建用户图形界面
   * 如果在星期天构建，则抛出异常
   */
  public void JavadocDemo() {
    Button b = new Button();
    add(b);
    if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
      throw new IllegalArgumentException("Never On A Sunday");
    }
  }

  public void paint(Graphics graphics) {
    int w = getSize().width, h = getSize().height;
    graphics.setColor(Color.YELLOW);
    graphics.fillRect(0, 0, w/2, h);
    graphics.setColor(Color.GREEN);
    graphics.fillRect(w/2, 0, w, h);
    graphics.setColor(Color.BLACK);
    graphics.drawString("Welcome to Java", 50, 50);
  }
}
