package ParallelPattern.Future;

public class FutureData implements Data {

    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();   //RealData已经注入，通知getResult()
    }

    @Override
    public synchronized String getResult() {   //会等待RealData构造完成
        while (!isReady){
            try{
                wait();  //一直等待，知道RealData被注入
            }catch (Exception e){

            }

        }
        return realData.result;   //由RealData实现
    }
}
