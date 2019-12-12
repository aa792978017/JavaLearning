package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model3;

import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;
import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.WhiteHuman;

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
