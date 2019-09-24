package Java.JUC;

/**
 * 测试守护线程和用户线程
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        daemonThread.setDaemon(true);   //调用setDaemon即可这样就能设置为守护线程
        daemonThread.start();
    }
}
