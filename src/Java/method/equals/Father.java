/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.method.equals;

import java.util.Objects;

public class Father {
  private String name;

  public Father(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Father father = (Father) o;
    return name.equals(father.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
