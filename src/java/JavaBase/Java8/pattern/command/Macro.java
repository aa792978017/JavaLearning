/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8.pattern.command;

import java.util.ArrayList;
import java.util.List;
// 发起者
public class Macro {

  private final List<Action> actions;

  public Macro() {
    actions = new ArrayList<>();
  }

  public void record(Action action) {
    actions.add(action);
  }

  public void run() {
    actions.forEach(Action::perform);
  }

}
