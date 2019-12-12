package java.DesignerPattern.ObserverPattern;

public interface Observer {
    //一旦发现被观察者有动作，做出反应
    public void update(String context);
}
