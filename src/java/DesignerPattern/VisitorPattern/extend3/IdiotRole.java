package java.DesignerPattern.VisitorPattern.extend3;

public class IdiotRole implements Role {
    @Override
    public void accept(AbsActor actor) {
        actor.act(this);
    }
}
