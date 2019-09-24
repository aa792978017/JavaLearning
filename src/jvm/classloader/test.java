package jvm.classloader;

public class test {

    public static int testReturn(){
        int i = 1;
        return i = 2;

    }

    /**
     * 类加载器有三类：
     * 根类加载器 会返回空值 因为是用c++实现的，出于安全考虑，对java程序员屏蔽了
     * 扩展加载器
     * 系统加载器，应用加载器，我们自己定义的类默认都是用这个加载器的
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        Class clazz = Class.forName("java.lang.String");
        Class test2 = Class.forName("jvm.classloader.test");
        System.out.println(test2.getClassLoader());

    }
     class Test1 {

    }
}
