package JavaBase.intefaceTest;

public interface TestInteface {
    public static final int MAX_VALUE = 1;
    //传统接口方法
    void hi();

    //java8新增的默认方法
    default void say(){
        System.out.println("speak");
        walk();
    }

    //java8新增的静态方法
    public static void walk(){

        System.out.println("i can walk");
    }

}
