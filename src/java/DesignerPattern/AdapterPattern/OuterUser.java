package java.DesignerPattern.AdapterPattern;

import java.util.HashMap;
import java.util.Map;

public class OuterUser implements IOuterUser {
    @Override
    public Map getUserBaseInfo() {
        HashMap baseInfoMap = new HashMap();
        baseInfoMap.put("userName","xiaowang");
        baseInfoMap.put("mobileNumber","137xxxxyyyy");
        return baseInfoMap;
    }

    @Override
    public Map teUserHomeInfo() {
        HashMap homeInfoMap = new HashMap();
        homeInfoMap.put("address","guangdong");
        homeInfoMap.put("homeNumber","xxxxx");
        return homeInfoMap;
    }
}
