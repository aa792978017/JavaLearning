package java.JavaBase.JUC;

public class ThreadLocalTest {
    static void print(String str){
        //打印当前线程本地内存中的localVariable变量值
        System.out.println(str + ":"+ localVariable.get());
        //清除当前线程本地内存中的localVariable的值
        //localVariable.remove();
    }
    //会给每个访问该变量的线程设置一个副本,线程都值访问自己的本地副本
    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread threaOne = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1的本地localVariable值
                localVariable.set("threadOne local variable");
                print("threadOne");
                System.out.println("threadOne remove after " + " : " +localVariable.get());
            }
        });

        Thread threaTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程2的本地localVariable值
                localVariable.set("threadTwo local variable");
                print("threadTwo");
                System.out.println("threadTwo remove after " + " : " +localVariable.get());
            }
        });
        threaOne.start();
        threaTwo.start();
    }
}
