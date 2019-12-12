package java.DesignerPattern.BuilderPattern;

import java.util.ArrayList;
//具体建造者，实现具体建造逻辑
public class BenzBuilder extends CarBuilder {

    private BenzModel benz = new BenzModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {

        return this.benz;
    }
}
