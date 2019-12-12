package JavaBase.Reflect;

import JavaBase.Reflect.Cat;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 测试java反射,演示几个方法
 * 1.获取类的Class对象
 * 2.通过Class对象获取类的成员变量
 * 3.通过Class对象获取类的方法,构造方法等,并调用方法
 * 4.通过获取到构造方法获取该类的一个实例
 *
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //1.通过类名.class来获取到字节码文件对象，这里我们就已经获取到了需要的字节码文件
        Class catClass = Class.forName("java.JavaBase.Reflect.Cat");
//        通过Object对象中的getClass()方法获取
        Cat cat = new Cat();
//        Class catClass = cat.getClass();
        System.out.println("输出该类: " + catClass);


        //2.获取所有的公共成员变量

        Field[] fields = catClass.getFields();
        System.out.println("遍历获取到的公共成员变量");
        for (Field f : fields){
            //这里只能获取到public修饰的成员变量
            //私有的成员变量需要通过catClass.getDeclaredField()方法获取（暴力获取私有变量）
            System.out.println(f);
        }

//        3.获取所有公有方法，私有方法通过catClass.getDeclaredMethods()获取
        Method[] catClassMethods = catClass.getMethods();
        System.out.println("遍历获取到的所有公共方法");
        for(Method m : catClassMethods){
            //输出私有方法名
            System.out.println(m);
        }

        //   获取所有私有方法
        Method[] catDeclaredMethods = catClass.getDeclaredMethods();
        System.out.println("遍历获取到的所有私有方法");
        for(Method m : catDeclaredMethods){
            //输出私有方法名
            System.out.println(m);
        }
        //运行获取到的第一个方法,这里必须传入该类的对象和可变参数
        catClassMethods[0].invoke(cat,null);

        System.out.println("获取构造方法");
        Constructor catCon = catClass.getConstructor();
        System.out.println(catCon);

        //4.通过构造方法获取新的实例
        System.out.println("通过构造方法获取新的实例");
        Object newCat = catCon.newInstance();
        System.out.println(newCat);


        testFunction2();


    }

    /**
     * 将已存在的ArrayList<Integer>集合中添加一个字符串数据
     * @throws Exception
     */
    public static void testFunction1() throws Exception{
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(new Integer(10));
        list.add(new Integer("24"));
        //因为该集合定义的元素类型时Integer,所有放入"我是字符串"的时候报错,编译失败
//        list.add("我是字符串");
        //这里通过反射技术获取Arraylist的add()方法,获取到擦除了泛型的方法,来添加"100"
        //1.获取ArrayList的Class对象
        Class listClass = list.getClass();
        //2.通过Class对象获取add()方法
        Method addMethod = listClass.getMethod("add",Object.class);
        //3.调用我们实例出的list对象,调用add方法
        addMethod.invoke(list, "我是字符串");
        //4.输出list,查看元素
        System.out.println(list);
    }

    /**
     * 通过反射配置文件
     */
    public static void testFunction2() throws Exception{
        //通过流读取配置文件,并加载到Properties对象
        Properties properties = new Properties();
        properties.load(new FileInputStream("这里填入自己的配置文件路径"));

        //获取里面的键值对,获取属性值
        String className = properties.getProperty("className");
        int  age = Integer.valueOf(properties.getProperty("age"));
        String name = properties.getProperty("name");
        String methodName = properties.getProperty("method");
        System.out.println(className);

        //通过类名获取Class对象,并获取其中的有参构造方法
        Class c = Class.forName(className);
        Constructor constructor = c.getConstructor(int.class,String.class);

        //通过获取到的构造方法获取实例,传入配置文件获取的参数,并实例化所需对象
        Object obj = constructor.newInstance(age,name);
        System.out.println(obj);

        //获取cat类的eat方法
        Method method = c.getMethod(methodName,null);
        //调用eat方法
        method.invoke(obj,null);
    }
}
