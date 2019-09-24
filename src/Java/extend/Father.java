package Java.extend;

public class Father {
    public int age = 30;
    public Father getObject(){
        return new Father();
    }

    public void call(){
        System.out.println("father");
    }
    public static void main(String[] args) {
        System.out.println("i am father");
    }
}
