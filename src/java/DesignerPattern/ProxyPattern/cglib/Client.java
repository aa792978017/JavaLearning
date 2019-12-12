package java.DesignerPattern.ProxyPattern.cglib;


import java.DesignerPattern.ProxyPattern.RealSubject;
import java.DesignerPattern.ProxyPattern.Subject;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Created by cat on 2017-02-27.
 */
public class Client {

  public static void main(String[] args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(RealSubject.class);
    enhancer.setCallback(new DemoMethodInterceptor());
    Subject subject = (Subject) enhancer.create();
    subject.hello();
  }
}
