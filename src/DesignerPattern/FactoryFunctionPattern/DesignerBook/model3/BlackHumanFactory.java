package DesignerPattern.FactoryFunctionPattern.DesignerBook.model3;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.BlackHuman;
import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;

/**
 * 黑种人生产工厂
 */
public class BlackHumanFactory extends AbstractHumanFactory {
    /**
     * 创建一个黑种人
     * @return
     */
    @Override
    public Human createHuman() {
        return new BlackHuman();
    }
}
