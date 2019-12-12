package JavaBase.Reflect;

/**
 * 这里新建一个类用于反射获取类
 */
public class Cat {
    private int age;
    public String name;

    public Cat(){
        System.out.println("我是构造方法,负责构造猫");
    }

    public Cat(int age, String name){
        this.age = age;
        this.name = name;
    }
    /**
     * 公共方法
     */
    public void eat(){
        System.out.println("吃鱼咯~~~");
    }
    /**
     * 私有方法
     */
    private void sleep(){
        System.out.println("lay 了，睡觉，谁也别过来逗我");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
