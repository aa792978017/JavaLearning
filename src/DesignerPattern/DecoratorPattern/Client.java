package DesignerPattern.DecoratorPattern;

public class Client {
    public static void main(String[] args) {
        //定义基本的核心业务
        Component c = new ConcreateComponent();
        //把核心业务传入装饰器
        Decorator d = new Decorator(c);
        //定义第一层装饰器
        Decorator d11 = new ConcreateDecoratorA(d);
        //定义第二层装饰器
        Decorator d12 = new ConcreateDecoratorB(d11);
        d12.operate();


    }
}
