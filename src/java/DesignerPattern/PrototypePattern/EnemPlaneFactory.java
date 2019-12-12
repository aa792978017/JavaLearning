package java.DesignerPattern.PrototypePattern;

public class EnemPlaneFactory {
    //用痴汉模式造一个敌机原型
    private static EnemyPlane protoType = new EnemyPlane(200);

    //获取敌机克隆实例
    public static EnemyPlane getInstance(int x) throws CloneNotSupportedException {
        EnemyPlane clone = protoType.clone();
        clone.setX(x);
        return clone;

    }
}
