/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8;

import java.util.function.BinaryOperator;

/**
 * lambda表达式学习
 */
public class Demo2 {

  public static void main(String[] args) {
    // 该lambda表达式实现了Runable接口，该接口只有一个方法，没有参数，返回类型为void
    // 无参lambda
    Runnable runner1 = () -> System.out.println("Hello world");
    new Thread(runner1).run();
    new Thread(() -> System.out.println("Hello world")).run();

    // lambda表达式不仅可以是一行表达式，也可以是一个代码块，用{}括起来
    new Thread(() -> {
      int a = 1; int b = a;
      System.out.println(a + b);
    }).run();

    // 创建一个函数, 这个add是指一个函数，用于计算x，y的和， x，y的类型都是编译器推断得出的
    BinaryOperator<Integer> add = (x, y) -> x + y;

    // 指定类型, Lambda表达式的类型依赖于上下文环境，是由编译器推断出来，建议指定类型，可读性高
    BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;

    // lambda表达式引用既成事实上的final变量
    // 这也是为何称lambda闭包的原因，未赋值的变量与周边环境隔离起来，进而被绑定道一个特定的值
    String name = "tom";
    // name = "12"; // 如果加上这段代码，会报错，因为这时候，name就不是既成的值了
    new Thread(() -> System.out.println(name)).run(); // 这里传进去的tom，而不是引用

    // lambda表达式本身的类型：函数接口
    // 函数接口是指只有一个抽象方法的接口，用作lambda表达式的类型


  }
}
