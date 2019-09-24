package DesignerPattern.AbstractFactoryPattern;

/**
 * 汽车工作车间抽象类
 */
public abstract class CarWorkShop {
    //生产宝马车的车间
    public abstract AbstractBaoMa createBaoMa();
    //生产奔驰车的车间
    public abstract AbstractBenChi createBenChi();
}
