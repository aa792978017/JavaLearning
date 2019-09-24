package DesignerPattern.StatePattern;

public class OpeningState extends LiftState {
    @Override
    public void open() {
        System.out.println("电梯门开着...");
    }

    @Override
    public void close() {
        //状态修改
        super.context.setLiftState(Context.closingState);
        //动作委托为CloseState来执行
        super.context.getLiftState().close();
    }

    @Override
    public void run() {
        //do nothing
    }

    @Override
    public void stop() {
        //do nothing
    }
}
