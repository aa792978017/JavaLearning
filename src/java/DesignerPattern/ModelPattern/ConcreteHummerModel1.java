package java.DesignerPattern.ModelPattern;

public class ConcreteHummerModel1 extends HummerModel {
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
        System.out.println("悍马h1发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马h1停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马h1鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马h1引擎声音是这样的...");
    }
}
