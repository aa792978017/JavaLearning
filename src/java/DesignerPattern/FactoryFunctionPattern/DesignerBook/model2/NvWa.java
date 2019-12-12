package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model2;

import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.BlackHuman;
import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;
import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.WhiteHuman;
import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.YellowHuman;

/**
 * 缩小为简单工厂模式
 * 如果一个模块只需要一个工厂类,我们没有必要把它实例化出来
 * 使用静态方法就可以了
 * 缺点：扩展比较困懒，不符合开闭原则
 */
public class NvWa {
    public static void main(String[] args) {

        //女娲生成白种人
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        //白种人开始活动
        whiteHuman.getColor();
        whiteHuman.talk();
        //女娲生成黄种人
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
        //女娲生成黑种人
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
    }
}
