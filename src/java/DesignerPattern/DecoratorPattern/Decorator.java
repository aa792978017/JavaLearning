package java.DesignerPattern.DecoratorPattern;

/**
 * 装饰角色抽象
 *
 */
public class Decorator extends Component{

    private Component component = null;

    //把要装饰的类传入构造方法
    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operate() {
        //执行基本核心类的工作
        component.operate();
    }
}
