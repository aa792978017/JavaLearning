package Java.spring;


import Java.spring.DI.DI1.SimpleInject;
import Java.spring.DI.DI2.SimpleSingleton;

public class ServiceA {

    @SimpleSingleton
    @SimpleInject
    private ServiceB serviceB;

    public void callB(){
        serviceB.action();
    }
}
