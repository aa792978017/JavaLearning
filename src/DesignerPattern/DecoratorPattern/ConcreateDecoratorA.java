package DesignerPattern.DecoratorPattern;

public class ConcreateDecoratorA extends Decorator{

    //传入上一层的装饰类
    public ConcreateDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        //在执行上一层的装饰方法前执行这一层
        this.decorateAction();
        //执行上一层的装饰逻辑
        super.operate();
    }

    private void decorateAction(){
        System.out.println("这一层引入的装饰逻辑,在执行上一层装饰前，执行这一层的: A");
    }


}
