package DesignerPattern.MementoPattern.extend1;

import com.sun.management.VMOption;
import com.sun.org.apache.xpath.internal.operations.Or;

public class Originator implements Cloneable {

    private String state = "";

    //创建一个备份
    public Originator createMemento() throws CloneNotSupportedException {
        return this.clone();
    }
    //恢复一个备份
    public void restoreMemento(Originator originator){
        this.setState(originator.getState());
    }
    //克隆当前对象
    @Override
    protected Originator clone() throws CloneNotSupportedException {

        try{
            return (Originator)super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
