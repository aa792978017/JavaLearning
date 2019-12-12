package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model2;

import java.DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;


public class HumanFactory {
    /**
     * 作为工具方法直接调用即可
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Human> T createHuman(Class<T> clazz){
        Human human = null;
        try{
            human = (Human) Class.forName(clazz.getName()).newInstance();
        }catch (Exception e){
            System.out.println("人种产生错误");
        }
        return (T) human;
    }
}
