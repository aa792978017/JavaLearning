package DesignerPattern.BuilderPattern;

public class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("宝马车的跑起来是这样的...");
    }

    @Override
    protected void stop() {
        System.out.println("宝马车的是这样停车的...");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马车的喇叭声是这样的...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马车的引擎声是这样的...");
    }
}
