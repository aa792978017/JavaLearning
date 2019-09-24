package ParallelPattern.Future.jdkFuture;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构造futureTask
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        执行FutureTask，相当于上例中的client.request("a")的请求
//        在这里开启线程进行RealData的call()执行
        executorService.submit(future);
        System.out.println("请求完毕");
        try{
            //这里处理别的业务
            Thread.sleep(2000);
        }catch (Exception e){

        }
        System.out.println("数据 = " + future.get());
    }
}
