package DesignerPattern.FactoryFunctionPattern.DesignerBook.model3;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;

/**
 * 抽象创建工厂
 */
public abstract class AbstractHumanFactory {
    /**
     * 创建人类
     * @return
     */
    public abstract Human createHuman();
}
