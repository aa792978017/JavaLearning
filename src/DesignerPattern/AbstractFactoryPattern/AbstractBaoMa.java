package DesignerPattern.AbstractFactoryPattern;

/**
 * 宝马车公司
 */
public abstract class AbstractBaoMa implements Car{
    private String ban = "BaoMa";

    @Override
    public void run() {
        System.out.println("I am BaoMa, I can run fast");
    }

}
