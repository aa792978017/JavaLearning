package DesignerPattern.AdapterPattern;

import java.util.HashMap;
import java.util.Map;
//适配器角色
public class OuterUserInfo extends OuterUser implements IUserInfo {
    private Map baseInfo = new HashMap();
    private Map homeInfo = new HashMap();

    @Override
    public String getUserName() {
        String userName = (String) this.baseInfo.get("userName");
        System.out.println(userName);
        return null;
    }

    @Override
    public String getHomeAddress() {
        String homeAddress = (String) this.homeInfo.get("address");
        System.out.println(homeAddress);
        return null;
    }

    @Override
    public String getMobileNumber() {
        return null;
    }
}
