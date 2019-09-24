package DesignerPattern.ObserverPattern;

import java.util.Observable;

public class Client {
    public static void main(String[] args) {
        //定义观察者
        Observer lisi = new LiSi();
        Observer wangsi = new WangSi();
        //定义被观察者
        HanFeiZi hanFeiZi = new HanFeiZi();
        hanFeiZi.addObserver(lisi);
        hanFeiZi.addObserver(wangsi);
        //韩非子开始活动
        hanFeiZi.havingBreakfast();
        hanFeiZi.havingFun();

    }
}
