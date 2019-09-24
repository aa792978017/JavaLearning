package Java.zhujie;


import Java.zhujie.DI.DI1.SimpleInject;
import Java.zhujie.DI.DI2.SimpleSingleton;

public class ServiceA {

    @SimpleSingleton
    @SimpleInject
    private ServiceB serviceB;

    public void callB(){
        serviceB.action();
    }
}
