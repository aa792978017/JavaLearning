package java.DesignerPattern.FlyweightPattern;

public class SignInfo4Pool extends SignInfo {
    private String key; //外部状态，内部状态为基类里面的成员变量

    public SignInfo4Pool(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
