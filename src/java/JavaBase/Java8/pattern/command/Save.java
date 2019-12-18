/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8.pattern.command;
// 具体命令者
public class Save implements Action {

  private final Editor editor;

  public Save(Editor editor) {
    this.editor = editor;
  }

  @Override
  public void perform() {
    editor.save();
  }
}
