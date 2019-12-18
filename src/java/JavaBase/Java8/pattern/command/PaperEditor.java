/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8.pattern.command;

public class PaperEditor implements Editor {

  @Override
  public void save() {
    System.out.println("save paper editor");
  }

  @Override
  public void open() {
    System.out.println("open paper editor");
  }

  @Override
  public void close() {
    System.out.println("close paper editor");
  }
}
