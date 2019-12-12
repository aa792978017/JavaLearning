package java.DesignerPattern.ObserverPattern;

public class LiSi implements Observer {

    //观察者观察到被观察者活动后的动作
    @Override
    public void update(String context) {
        System.out.println("李斯：观察到韩非子活动，开始向老板回报了...");
        this.reportToQinShiHuang(context);
        System.out.println("李斯：汇报完毕...");
    }

    //汇报给秦始皇
    private void reportToQinShiHuang(String context) {
        System.out.println("李斯：报告，秦老板，韩非子有活动了--> " + context);
    }
}
