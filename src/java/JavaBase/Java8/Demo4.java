/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8;


import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

/**
 * Optional类学习
 */
public class Demo4 {

  public static void main(String[] args) throws Throwable {
    List<Track> tracks = new ArrayList<>();
    tracks.add(new Track("track1", 1));
    tracks.add(new Track("track2", 2));
    tracks.add(new Track("track3", 3));
    List<Artist> allArtist = new ArrayList<>();
    allArtist.add(new Artist("jack","London"));
    allArtist.add(new Artist("wangchang","China"));
    allArtist.add(new Artist("tom","London"));
    Album album = new Album("album", tracks, allArtist);

    // 基本类型的性能提升方法，减少内存使用和装箱、拆箱的开销
    // stream对整型、长整型、双浮点型做了特殊处理
    // 返回值基本类型时，使用mapToXXX替换map，有mapToInt，mapToLong，mapToDouble等
    IntSummaryStatistics trackLengthStats =
        album.getTracks()
            .mapToInt(track -> track.getLength())
            .summaryStatistics();
    // summaryStatistics()方法 获取各种统计值
    System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
        trackLengthStats.getMax(),
        trackLengthStats.getMin(),
        trackLengthStats.getAverage(),
        trackLengthStats.getSum());


    // 如果定义一个函数接口，且最后用做函数接口，则必须加上@FunctionalInterface
    // api中自带的函数接口都带有该注解，不加该注解，编译会失败

    // 关于默认方法的工作原理：
    // 1、类胜于接口，声明相同，继承优先
    // 2、子类胜于父类，子类和父类都有，调用子类的
    // 3、没有规则三，如果不符合上述两个规则，则子类需要实现该方法；或者将方法声明改为抽象方法

    // Optional对象 目的：用于替换null值，鼓励程序员适时检查变量是否为空避免代码缺陷；第二，可以将空的值文档化
    // 用法:
    // 1、创建Optional对象
    Optional<String> a = Optional.of("a");
    // 2、获取Optional对象的值 get()方法
    assert "a" == a.get();
    // 3、获取空Optional对象
    Optional emptyOptional = Optional.empty();
    // 4、如果变量为空，将空值转换为null的Optional对象，否则使用of()，返回非空的Option对象
    String alsoNull = null;
    Optional alsoEmtpyOptional = Optional.ofNullable(alsoNull);
    // 5、检查Optional对象是否有值，返回布尔值;
    a.isPresent();
    // 重载方法还能传入一个Consumer参数的lambda表达式
    // a.isPresent(() -> createString()); // 这里传入一个lambda表达式，当值为不为空，执行函数

    // 6、Optional对象为空时调用某函数,返回执行结果,对象不为空时，不会执行传入的方法！！！
    emptyOptional.orElseGet(() -> createString());

    // 7、对象为空时，返回a
    emptyOptional.orElse(a);
    // 如果传入的是方法，尽管对象不为空，也会执行方法；如果希望非空时不执行，就用orElseGet()
    emptyOptional.orElse(createString());

    // 对象为空时抛出指定异常，这样我们就能自定义抛出的异常了，而不是什么时候都抛出NullPointException；
    emptyOptional.orElseThrow(() ->new NullPointerException());

    // map方法
    User user = new User("tom", 10);
    String name = Optional.ofNullable(user)
        .map(u -> u.getName())
        .orElse("default");

    // flatMap方法, 需要传入返回值为Optional的方法
    String optionalName = Optional.ofNullable(user)
        .flatMap(u -> u.getOptionalName())
        .orElse("default");

    // filter 过滤，如果测试结果为false，返回一个空的Optional
    Optional<User> filterUser = Optional.ofNullable(user)
        .filter(u -> u.getName() !=null && u.getName().startsWith("t"));

    // Optional主要用在返回类型，可以在返回不到值得时候，做一些替代处理，这样代码可读性很高
    List<User> users = new ArrayList<>();
    User use = users.stream().findFirst().orElse(new User("default", 10));

    // 在一直值可能为空时，使用Optional替换null值；不要作为类成员变量，无法序列化，

  }
  private static String createString() {
    return "test";
  }


}

class User {
  private String name;
  private int age;

  public Optional<String> getOptionalName(){
    return Optional.ofNullable(this.name);
  }

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}

