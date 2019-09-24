package ParallelPattern.Future.jdkFuture;

import java.util.concurrent.Callable;

public class RealData implements Callable {
    private String para;
    public RealData(String para){
        this.para = para;
    }

    @Override
    public Object call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < 10; i++){
            sb.append(para);
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
        }

        return sb.toString();
    }
}
