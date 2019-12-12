package java.DesignerPattern.BuilderPattern;

import java.util.ArrayList;

public abstract class CarModel {

    //该变量为基本方法的执行顺序
    private ArrayList<String> sequence = new ArrayList<>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    final public void run(){
        for (int i = 0; i < this.sequence.size(); i++){
            String actionName = this.sequence.get(i);
            if (actionName.equalsIgnoreCase("start")){
                this.start();
            }else if (actionName.equalsIgnoreCase("stop")){
                this.stop();
            }else if (actionName.equalsIgnoreCase("engine boom")){
                this.engineBoom();
            }else if (actionName.equalsIgnoreCase("alarm")){
                this.alarm();
            }
        }

    }
    //把执行顺序设置上
    final public void setSequence(ArrayList<String> sequence){
        this.sequence = sequence;
    }
}
