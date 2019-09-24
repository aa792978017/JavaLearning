package ParallelPattern.Future;

public class Client {
    public Data request(final  String queryStr){
        final  FutureData future = new FutureData();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);   //因为RealData的构建很慢，所以在单独的线程中进行
                future.setRealData(realData);
            }
        }.start();
        return future;
    }
}
