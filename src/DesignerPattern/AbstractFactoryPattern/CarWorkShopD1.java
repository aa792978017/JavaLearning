package DesignerPattern.AbstractFactoryPattern;

/**
 * 生产排量D1汽车的车间
 */
public class CarWorkShopD1 extends CarWorkShop {
    @Override
    public AbstractBaoMa createBaoMa() {
        return new BaoMaD1();
    }

    @Override
    public AbstractBenChi createBenChi() {
        return new BenChiD1();
    }
}
