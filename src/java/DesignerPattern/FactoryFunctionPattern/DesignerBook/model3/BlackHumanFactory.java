package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model3;

import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.BlackHuman;
import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;

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
