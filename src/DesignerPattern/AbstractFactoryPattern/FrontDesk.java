package DesignerPattern.AbstractFactoryPattern;

import java.util.HashMap;
import java.util.Map;

public class FrontDesk {
    /**
     * 汽车工厂
     * 一直运作
     */
    private final static Map<String,CarWorkShop> carFactory = new HashMap<>();

    public static <T extends CarWorkShop> T callCarFactory(Class<T> c){
        //我们需要联系一个具体的车间
        CarWorkShop carWorkShop = null;
        //如果车间已经在工作,就直接联系
        if (carFactory.containsKey(c.getSimpleName())){
            carWorkShop = carFactory.get(c.getSimpleName());
        }else {
            try {
                //如果车间没有开始工作,就通知车间开始运作,有定单来了
                carWorkShop = (CarWorkShop) Class.forName(c.getName()).newInstance();
                carFactory.put(c.getSimpleName(),carWorkShop);
            }catch (Exception ex){
                System.out.println("联系汽车厂失败");
            }
        }
        return (T) carWorkShop;
    }

}
