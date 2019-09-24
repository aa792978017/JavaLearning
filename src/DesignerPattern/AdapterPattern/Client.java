package DesignerPattern.AdapterPattern;

public class Client {

    public static void main(String[] args) {
        IUserInfo yougGirl = new OuterUserInfo();
        for (int i = 0; i < 10; i++){
            yougGirl.getMobileNumber();
        }

    }
}
