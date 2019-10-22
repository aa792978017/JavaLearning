/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.method.equals;
import java.util.Objects;

public class Son extends Father {

  private int school;

  public Son(String name, int school) {
    super(name);
    this.school = school;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Son son = (Son) o;
    return getSchool() == son.getSchool();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getSchool());
  }

  public int getSchool() {
    return school;
  }

  public void setSchool(int school) {
    this.school = school;
  }


  public static void main(String[] args) {
    Son s1 = new Son("A", 1);
    Son s2 = new Son("B", 1);
    Son s3 = new Son("A", 2);
    Father f1 = new Father("A");
    Father f2 = new Son("A",1);
    System.out.println(f1.equals(f2));
//    System.out.println(f2.equals(f1));
//    System.out.println(s1.equals(f1));
//    System.out.println(f1.equals(s1));
  }
}
