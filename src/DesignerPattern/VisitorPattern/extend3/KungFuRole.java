package DesignerPattern.VisitorPattern.extend3;

public class KungFuRole implements Role {
    @Override
    public void accept(AbsActor actor) {
        actor.act(this);   //调用实际的actor类型的act(KungFuRole),如果没有，去基类找
    }
}
