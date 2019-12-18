/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8.pattern.command;

// 命令接受者
public interface Editor {

  public void save();

  public void open();

  public void close();

}
