package DesignerPattern.FactoryFunctionPattern.DesignerBook.model1;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.BlackHuman;
import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;
import DesignerPattern.FactoryFunctionPattern.DesignerBook.WhiteHuman;
import DesignerPattern.FactoryFunctionPattern.DesignerBook.YellowHuman;

/**
 * 造人的女娲
 */
public class NvWa {
    //声明阴阳炉,全世界只有一个,神器,无法破坏,无法修改
    static final AbstractHumanFactory yingYangLu = new HumanFactory();
    public static void main(String[] args) {
        //女娲生成白种人
        Human whiteHuman = yingYangLu.createHuman(WhiteHuman.class);
        //白种人开始活动
        whiteHuman.getColor();
        whiteHuman.talk();
        //女娲生成黄种人
        Human yellowHuman = yingYangLu.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
        //女娲生成黑种人
        Human blackHuman = yingYangLu.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
    }
}
