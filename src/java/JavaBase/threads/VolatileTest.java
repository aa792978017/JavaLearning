package java.JavaBase.Threads;

public class VolatileTest {

    private static Integer[] listInt;
    //这里使用一个简单的单例来演示,目的是知道这个单例会被实例化多少次.
    public static VolatileTest volatileTest;
    private VolatileTest(){
        listInt = new Integer[20];
        for (int i = 0; i < 20; i++){
            listInt[i] = 1;
        }

    }
    public static VolatileTest getInstance(){
        if (volatileTest == null){
            volatileTest = new VolatileTest();
        }
        return volatileTest;
    }

    public static void main(String[] args) throws InterruptedException {
        //
//        Thread threadOne = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (volatileTest != null){
//
//                    System.out.println("threadOne : volatileTest is not null" + "  list[15]:" + listInt[15]);
//                }else {
//                    System.out.println("threadOne : volatileTest is null");
//                }
//
//            }
//        });
//        Thread threadtwo = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (volatileTest != null){
//                    System.out.println("threadTwo : volatileTest is not null" + "  list[15]:" + listInt[15]);
//                }else {
//                    System.out.println("threadTwo : volatileTest is null");
//                }
//
//            }
//        });
//        VolatileTest.getInstance();
//        threadOne.start();
//        threadtwo.start();


    }
}
