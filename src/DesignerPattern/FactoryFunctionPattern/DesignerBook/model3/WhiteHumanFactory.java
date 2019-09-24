package DesignerPattern.FactoryFunctionPattern.DesignerBook.model3;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;
import DesignerPattern.FactoryFunctionPattern.DesignerBook.WhiteHuman;

/**
 * 白种人生产工厂
 */
public class WhiteHumanFactory extends AbstractHumanFactory {
    /**
     * 创建一个白种人
     * @return
     */
    @Override
    public Human createHuman() {
        return new WhiteHuman();
    }
}
