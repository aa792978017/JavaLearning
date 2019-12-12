package java.DesignerPattern.ObserverPattern;

public class WangSi implements Observer {
    //观察者观察到被观察者活动后的动作
    @Override
    public void update(String context) {
        System.out.println("王斯：观察到韩非子活动，自己也开始活动了...");
        this.reportToQinShiHuang(context);
        System.out.println("王斯：哭了...");
    }

    //一看韩非子有活动，他就哭
    private void reportToQinShiHuang(String context) {
        System.out.println("王斯：因为" + context + ",所以我悲伤呀！");
    }
}