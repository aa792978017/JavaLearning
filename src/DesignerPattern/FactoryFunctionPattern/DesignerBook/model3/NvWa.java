package DesignerPattern.FactoryFunctionPattern.DesignerBook.model3;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;

/**
 * 多个工厂类模式
 * 当我们遇到初始化一个对象必将复杂的时候，所有产品类都放到一个工厂方法里面进行初始话会使代码结构不清晰
 * 可能会导致方法十分巨大，这时候可以为每种产品定义一个具体工厂类，给调用者自行选择
 * 好处：创建类的职责清晰，结构简单
 * 缺点：扩展和维护难度提高了：每个扩展一个具体产品类，都需要建立一个相应的工厂类
 */
public class NvWa {
    static AbstractHumanFactory humanFactory;
    public static void main(String[] args) {
        //使用白人工厂
        humanFactory = new WhiteHumanFactory();
        //女娲生成白种人
        Human whiteHuman = humanFactory.createHuman();
        //白种人开始活动
        whiteHuman.getColor();
        whiteHuman.talk();
        //更换为黄种人
        humanFactory = new YellowHumanFactory();
        //女娲生成黄种人
        Human yellowHuman = humanFactory.createHuman();
        yellowHuman.getColor();
        yellowHuman.talk();
        //更换为黑人工厂
        humanFactory = new BlackHumanFactory();
        //女娲生成黑种人
        Human blackHuman = humanFactory.createHuman();
        blackHuman.getColor();
        blackHuman.talk();
    }
}
