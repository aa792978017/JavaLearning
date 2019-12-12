package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model3;

import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;
import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.YellowHuman;

/**
 * 黄种人生产工厂
 */
public class YellowHumanFactory extends AbstractHumanFactory {
    /**
     * 创建一个黄种人
     * @return
     */
    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
