package Java.jvm.common;

public class Father {
    public static int i = 10;

    {
        System.out.println("父亲普通代码块 i:" + i);
    }

    static {
        System.out.println("父亲静态块: i" + i);
    }


    public Father(){
        System.out.println("父亲构造方法 i:" + i);
    }
}
