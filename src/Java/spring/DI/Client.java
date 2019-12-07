package Java.spring.DI;

import Java.spring.DI.DI1.SimpleContainer;
import Java.spring.DI.DI2.SimpleSingletonContainer;
import Java.spring.ServiceA;

public class Client {
    public static void main(String[] args) {
        ServiceA a = SimpleContainer.getInstance(ServiceA.class);
        a.callB();
        ServiceA a2 = SimpleSingletonContainer.getInstance(ServiceA.class);
        a2.callB();
    }
}
