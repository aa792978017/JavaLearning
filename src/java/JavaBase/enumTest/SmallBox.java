package java.JavaBase.enumTest;

import JavaBase.enumTest.Box;

public class SmallBox<T,E> extends Box {
    public SmallBox(T name, E type) {
        super(name, type);
    }

    public static void main(String[] args) {
        max(new SmallBox<String,String>("1","2"),
                new SmallBox<String,String>("3","4"));
    }
    //这里如果想子类不重写comparable方法的会只能用super通配
    public static <T,E extends Comparable<? super T>> T max(Box<T,E> box1,Box<T,E> box2){
        return null;
    }

}
