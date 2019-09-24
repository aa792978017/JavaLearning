package DesignerPattern.AbstractFactoryPattern;

/**
 * 奔驰车公司
 */
public abstract class AbstractBenChi implements Car{
    private String ban = "BenChi";
    @Override
    public void run() {
        System.out.println("I am BenChi, I can run very fast");
    }
    public abstract void displacement();
}
