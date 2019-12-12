package java.DesignerPattern.ModelPattern;

public class ConcreteHummerModel2 extends HummerModel {
    //默认要响喇叭
    private boolean alarmFlag = true;

    protected boolean isAlarm(){
        return this.alarmFlag;
    }
    //客户决定要不要鸣笛
    public void setAlarm(boolean isAlarm){
        this.alarmFlag = isAlarm;
    }

    @Override
    protected void start() {
        System.out.println("悍马h2发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马h2停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马h2鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马h2引擎声音是这样的...");
    }
}
