package DesignerPattern.FactoryFunctionPattern.DesignerBook.model5;

import DesignerPattern.FactoryFunctionPattern.DesignerBook.Human;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟初始化
 * 一个对象消费完以后不立即释放，保存状态，等待再次被使用
 *
 */
public class HumanFactory {
    private static final Map<String, Human> humanMap = new HashMap<>();
    public static synchronized <T extends Human> T createHuman(Class<T> c){
        Human human = null;
        String humanType = c.getSimpleName();
        if (humanMap.containsKey(humanType)){
            human = humanMap.get(humanType);
        }else{
            try{
                System.out.println("第一次生产: " + humanType);
                human = (Human) Class.forName(c.getName()).newInstance();
                humanMap.put(humanType,human);
            }catch (Exception e){
                System.out.println("生产人种失败");
            }
        }


        return (T) human;
    }
}
