/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8.pattern.command;
// lambda+命令模式
public class Client {

  public static void main(String[] args) {
    Macro macro = new Macro();
    Editor editor = new PaperEditor();
    // () -> editor.open() 可以理解为实现了Action接口的perform()方法,把实现代码传了进去
    // （使用lambda模式的主要场合）
    macro.record(() -> editor.open());
    macro.record(editor::open);  // 推荐这种写法，可读性高，简洁
    macro.record(() -> System.out.println(""));
    macro.run();

  }
}
