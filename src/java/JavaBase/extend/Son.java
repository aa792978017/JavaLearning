package JavaBase.extend;

public class Son extends Father {
    public int age = 3;
    @Override
    public Son getObject() {
        return new Son();
    }

//    @Override
//    public void call() {
//        System.out.println("son");
//    }
}
