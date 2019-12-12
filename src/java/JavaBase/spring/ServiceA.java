package java.JavaBase.spring;


import java.JavaBase.spring.DI.DI1.SimpleInject;
import java.JavaBase.spring.DI.DI2.SimpleSingleton;

public class ServiceA {

    @SimpleSingleton
    @SimpleInject
    private ServiceB serviceB;

    public void callB(){
        serviceB.action();
    }
}
