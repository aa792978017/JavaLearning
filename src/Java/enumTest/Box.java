package Java.enumTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个范型盒子
 * @param <T>
 */
public class Box<T,E> implements Comparable<T>{
    //盒子的名字
    private T name;
    //盒子要装东西的类型
    private E type;

    //前面这个括号T说明使用范型参数
    public <T> String enumFunction(T text){
        return (String) getName();
    }

    //范型参数可以有多个
    public static <U,V> Box<U,V> getBox(U name, V type){
        return new Box<>(name,type);
    }

    //只有继承或实现了List的参数才能传进来
    public <T extends List> List getBoxList(T list){
        return null;
    }

    //递归类型限制，只有实现了Comparable接口的类型才能传进来
    public static <T extends Comparable<T>> T compare(T arr){
        return null;
    }

    public static <T> void copy(ArrayList<T> arr, ArrayList<? extends T> src){

    }
    //这个跟上面是一样的，只能放入T或者T的子类但是上面的写法比较简略
    //public static <T,E extends T> void copy(ArrayList<T> dest, ArrayList<E> src){}



    public Box(T name, E type){

        this.name = name;
        this.type = type;
    }
    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public E getType() {
        return type;
    }

    public void setType(E type) {
        this.type = type;
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }
}
