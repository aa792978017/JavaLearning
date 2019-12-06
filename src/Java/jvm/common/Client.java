package Java.jvm.common;

public class Client {
    public static void main(String[] args) {
//        Father father = new Father();
            Son son = new Son();
//        if(-1 >> 1 == Integer.MAX_VALUE){
//            System.out.println(true);
//        }
        System.out.println(new String("ab").intern() == "a"+"b");
        }

}
