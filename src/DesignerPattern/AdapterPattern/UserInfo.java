package DesignerPattern.AdapterPattern;

public class UserInfo implements IUserInfo {
    @Override
    public String getUserName() {

        System.out.println("这是用户名");
        return null;
    }

    @Override
    public String getHomeAddress() {
        System.out.println("这是用户住址");
        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("这是联系电话");
        return null;
    }
}
