package DesignerPattern.BuilderPattern;

import java.util.ArrayList;

//具体建造者，实现具体建造逻辑
public class BMWBuilder extends CarBuilder {
    
    private BMWModel bmw = new BMWModel();
    
    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);    
    }

    @Override
    public CarModel getCarModel() {
        
        return this.bmw;
    }
}
