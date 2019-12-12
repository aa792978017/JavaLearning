package java.DesignerPattern.BuilderPattern;

import java.util.ArrayList;

//导演类，直到建造者，进一步封装
public class Director {

    private ArrayList<String> sequence = new ArrayList<>();

    private BenzBuilder benzBuilder = new BenzBuilder();

    private BMWBuilder bmwBuilder = new BMWBuilder();

    public BenzModel getABenzModel(){
        //清空顺序模板
        this.sequence.clear();
        //定义ABenzModel的顺序
        this.sequence.add("start");
        this.sequence.add("stop");
        //按照指定的顺序制造车
        this.benzBuilder.setSequence(sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    public BenzModel getBBenzModel(){
        //清空顺序模板
        this.sequence.clear();
        //定义BBenzModel的顺序
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        //按照指定的顺序制造车
        this.benzBuilder.setSequence(sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    public BMWModel getCBMWModel(){
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSequence(sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }


}
