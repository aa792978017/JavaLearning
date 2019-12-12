package java.JavaBase.spring.DI;

import java.JavaBase.spring.DI.DI1.SimpleContainer;
import java.JavaBase.spring.DI.DI2.SimpleSingletonContainer;
import java.JavaBase.spring.ServiceA;

public class Client {
    public static void main(String[] args) {
        ServiceA a = SimpleContainer.getInstance(ServiceA.class);
        a.callB();
        ServiceA a2 = SimpleSingletonContainer.getInstance(ServiceA.class);
        a2.callB();
    }
}
