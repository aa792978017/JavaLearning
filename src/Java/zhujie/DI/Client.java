package Java.zhujie.DI;

import Java.zhujie.DI.DI1.SimpleContainer;
import Java.zhujie.DI.DI2.SimpleSingletonContainer;
import Java.zhujie.ServiceA;

public class Client {
    public static void main(String[] args) {
        ServiceA a = SimpleContainer.getInstance(ServiceA.class);
        a.callB();
        ServiceA a2 = SimpleSingletonContainer.getInstance(ServiceA.class);
        a2.callB();
    }
}
