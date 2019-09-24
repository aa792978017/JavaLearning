package Java.JUC;


import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park");
                while (!Thread.currentThread().isInterrupted()){
                    LockSupport.park();
                }
                System.out.println("child thread unpark");
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpack");
        //中断子线程,如果不中断,就会一直处于while循环
        //其他线程调用了阻塞线程的interrupt()方法,设置了中断标记或者线程被虚假唤醒,则阻塞线程也会返回
        thread.interrupt();
//        String
    }


}
