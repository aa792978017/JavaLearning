package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model4;

public class Client {
    public static void main(String[] args) {
        Singleton singleton = SingletonFactory.getSingleton();
        singleton.doSomething();
    }
}
