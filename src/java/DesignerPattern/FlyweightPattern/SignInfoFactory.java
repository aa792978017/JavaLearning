package java.DesignerPattern.FlyweightPattern;

import java.util.HashMap;

public class SignInfoFactory {
    private static HashMap<String,SignInfo> pool = new HashMap<>();

    @Deprecated
    public static SignInfo getSignInfo(){
        return new SignInfo();
    }

    //从池中获取对象
    public static SignInfo getSingInfo(String key){
        SignInfo result = null;
        if(!pool.containsKey(key)){
            System.out.println(key + "---建立对象，放入池中");
            result = new SignInfo4Pool(key);
            pool.put(key,result);
        }else{
            result = pool.get(key);
            System.out.println(key + "---直接从池中取出");
        }
        return result;
    }
}
