package DesignerPattern.ModelPattern;

public class Client {
    public static void main(String[] args) {
        HummerModel h1 = new ConcreteHummerModel1();
        h1.setAlarm(false);
        h1.run();
        System.out.println();
        HummerModel h2 = new ConcreteHummerModel2();
        h2.setAlarm(true);
        h2.run();
    }
}
