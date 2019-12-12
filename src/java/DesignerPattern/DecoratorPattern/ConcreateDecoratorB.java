package java.DesignerPattern.DecoratorPattern;

/**
 * 具体的一层装饰类
 */
public class ConcreateDecoratorB extends Decorator{

    /**
     * 执行上层构造
     * @param component
     */
    public ConcreateDecoratorB(Component component) {
        super(component);
    }

    //执行上层装饰逻辑前，先执行该层的
    @Override
    public void operate() {
        this.decorateAction();
        super.operate();
    }

    //这一层的装饰逻辑
    private void decorateAction(){
        System.out.println("这一层引入的装饰逻辑,在执行上一层装饰前，执行这一层的: B");
    }
}
