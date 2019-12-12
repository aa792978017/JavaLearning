package JavaBase.extend;

public class Client {
    public static void main(String[] args) {
        Son son = new Son();
        son.call();
        System.out.println(son.age);
        Father fa = son;
        System.out.println(fa.age);
        fa.call();
        System.out.println();
        Father f1 = new Son();
        f1.call();
        System.out.println( f1.age);
    }
}
