package java.DesignerPattern.SingletonPattern;

public class Main {
   private static Main singleton;
   private Main(){}
   private static class SingletonHodler{
       private SingletonHodler(){
       }
       private static Main singleton = new Main();
    }
    public static Main getInstance(){
       return SingletonHodler.singleton;
    }
}
