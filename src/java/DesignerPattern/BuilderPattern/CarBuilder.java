package java.DesignerPattern.BuilderPattern;

import java.util.ArrayList;

//抽象建造者
public abstract class CarBuilder {
    //设置顺序模型，给我一个组装顺序
    public abstract void setSequence(ArrayList<String> sequence);
    //设置顺序后，利用这个顺序造车辆模型
    public abstract CarModel getCarModel();
}
