package java.DesignerPattern.ObserverPattern;

import java.util.ArrayList;

public class HanFeiZi implements Obserable, IHanFeiZi {
    //存放所有观察者
    private ArrayList<Observer> observers = new ArrayList<>();
    //被观察者活动1
    @Override
    public void havingBreakfast() {
        System.out.println("韩非子：开始吃饭");
        this.notifyObservers("韩非子在吃饭");

    }
    //被观察者活动2
    @Override
    public void havingFun() {
        System.out.println("韩非子：开始娱乐了");
        this.notifyObservers("韩非子在娱乐");
    }
    //添加观察者
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
    //删除观察者
    @Override
    public void deleteObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String context) {
        for (Observer observer : this.observers){
            observer.update(context);
        }
    }




}
