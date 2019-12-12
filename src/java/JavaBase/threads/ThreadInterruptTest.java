package java.JavaBase.Threads;

/**
 * 根据中断标志判断线程是否终止
 */
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //当前线程被中断时退出循环
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread() + " hello");
                }
            }
        });
        //开启子线程
        thread.start();
        //主线程休眠，让子线程工作
        Thread.sleep(1000);
        System.out.println("main thread interrput thread");
        //设置子线程中断标志为true
        thread.interrupt();
        System.out.println("main is over");
    }
}
