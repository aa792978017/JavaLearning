package Java.Threads;

/**
 * 理解interrupt() isInterrupted() interrupted()区别
 */
public class ThreadInterruptTest1 {
    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){

                }
            }
        });
        //启动子线程
        threadOne.start();
        //设置子线程中断标志
        threadOne.interrupt();
        //获取中断标志
        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        //获取中断标志并重置
        System.out.println("interrupted: " + threadOne.interrupted());
        //获取中断标志并重置， 这里跟上面的一样，都是获取主线程的中断标志，所以都是false
        System.out.println("interrupted: " + Thread.interrupted());
        //获取子线程的中断标志
        System.out.println("isInterrputed: " + threadOne.isInterrupted());

    }
}
