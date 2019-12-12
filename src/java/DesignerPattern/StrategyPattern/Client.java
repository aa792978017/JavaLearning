package java.DesignerPattern.StrategyPattern;

public class Client {
    public static void main(String[] args) {
        Context c = new Context(new ConcreateStrategyA());
        c.doAnything();
        c = new Context(new ConcreateStrategyB());
        c.doAnything();
    }
}
