package java.DesignerPattern.BuilderPattern;

public class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("奔驰车的跑起来是这样的...");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰车的是这样停车的...");
    }

    @Override
    protected void alarm() {
        System.out.println("奔驰车的喇叭声是这样的...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰车的引擎声是这样的...");
    }
}
