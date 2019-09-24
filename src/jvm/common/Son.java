package jvm.common;

public class Son extends Father {
    public static int j = 10;

    {
        System.out.println("孩子普通代码块 j:" + j);
    }


    static {
        System.out.println("孩子静态代码块 j:" + j);
        System.out.println("父亲的静态变量 i："+ i);
    }

    public Son(){
        System.out.println("我是孩子构造方法 j:"+j);
    }
}
