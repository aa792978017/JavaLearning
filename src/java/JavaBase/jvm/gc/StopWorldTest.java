package java.JavaBase.jvm.gc;

import java.util.HashMap;

/**
 * 测试stop the world
 * 测试jvm参数： -Xmx512M -Xms512M -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails
 */
public class StopWorldTest {
    public static class MyThread extends Thread{
        HashMap map = new HashMap();

        @Override
        public void run() {
            try{
                while (true){
                    if (map.size()*512/1024/1024 >= 400){
                        map.clear();
                        System.out.println("clean map");
                    }
                    byte[] b1;
                    for (int i = 0; i < 100; i++){
                        b1 = new byte[512];
                        map.put(System.nanoTime(),b1);
                    }
                    Thread.sleep(1);
                }
            }catch (Exception e){

            }
        }
    }

    public static class PrintThread extends Thread{
        public static final long starttime = System.currentTimeMillis();

        @Override
        public void run() {
            try{
                while (true){
                    long t = System.currentTimeMillis() - starttime;
                    System.out.println(t/1000+ "." + t%1000);
                    Thread.sleep(1);
                }
            }catch (Exception e){

            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        PrintThread p = new PrintThread();
        t.start();
        p.start();
    }
}
