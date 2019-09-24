package DesignerPattern.DecoratorPattern;



/**
 * 最基本的核心类
 * 将会被装饰
 */
public class ConcreateComponent extends Component {

    @Override
    public void operate() {
        System.out.println("I am the ConcreateComponent");
    }
}
