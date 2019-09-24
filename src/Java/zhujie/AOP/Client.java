package Java.zhujie.AOP;

import Java.zhujie.ServiceA;

import javax.xml.ws.Service;

public class Client {
    public static void main(String[] args) {
        ServiceA a = CGLibContainer.getInstance(ServiceA.class);
        a.callB();
    }
}
