package Java.spring.AOP;

import Java.spring.ServiceA;

import Java.spring.ServiceB;

public class Client {
    public static void main(String[] args) {

        ServiceB b = CGLibContainer.getInstance(ServiceB.class);
        b.action();

        System.out.println("---------------------------------------");
        ServiceA a = CGLibContainer.getInstance(ServiceA.class);
        a.callB();

    }
}
