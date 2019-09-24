package DesignerPattern.FactoryFunctionPattern.DesignerBook.model5;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;
import DesignerPattern.FactoryFunctionPattern.DesignerBook.WhiteHuman;

/**
 * 延迟初始化
 * 第一次产生产品类的时候，把它存到一个Map里，避免下一次使用同一个类的时候需要重新实例化
 * 优点：在需要重用的时候节省时间
 * 缺点：如果不常多次使用，就会浪费一定空间
 */
public class NaWa {

    public static void main(String[] args) {
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
        Human whiteHuman2 = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman2.getColor();
        whiteHuman2.talk();
    }
}
