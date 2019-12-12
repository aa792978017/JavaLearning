package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model1;

import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;

/**
 * 定义抽象工厂
 */
public abstract class AbstractHumanFactory {
    public abstract <T extends Human> T createHuman(Class<T> c);
}
