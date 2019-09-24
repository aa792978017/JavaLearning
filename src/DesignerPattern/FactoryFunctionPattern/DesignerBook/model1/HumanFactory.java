package DesignerPattern.FactoryFunctionPattern.DesignerBook.model1;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;

/**
 * 具体的人种生成工厂
 * 这里通过反射获取实例
 */
public class HumanFactory extends AbstractHumanFactory {
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try{
            //产生一个人,通过反射获取
            human = (T)Class.forName(c.getName()).newInstance();
        }catch (Exception ex){
            //造人失败失败报错
            System.out.println("人种生成错误！");
        }
        return (T) human;
    }
}
