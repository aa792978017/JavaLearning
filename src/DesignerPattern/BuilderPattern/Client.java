package DesignerPattern.BuilderPattern;

public class Client {
    public static void main(String[] args) {
        Director director = new Director();
        //需要5台ABenz
        for(int i = 0; i < 100; i++){
            director.getABenzModel().run();
        }
        //需要2台BBenz
        for(int i = 0; i < 100; i++){
            director.getBBenzModel().run();
        }
        //需要1台CBMW
        for(int i = 0; i < 100; i++){
            director.getCBMWModel().run();
        }
    }
}
