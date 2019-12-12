package java.JavaBase.jvm.classloader.HotDeployDemo;

import java.io.File;

public class HotDeployDemo {
    private static final String CLASS_NAME = "Java.jvm.classloader.HotDeployDemo.HelloServiceImpl";  //class名
    private static final String FILE_NAME = "/home/voidcc/桌面/JavaLearning/out/production/JavaLearning/Java.jvm/classloader/HotDeployDemo/HelloServiceImpl.class";  //文件路径
    private static volatile IHelloService helloService;

    //使用单例创建实例
    public static IHelloService getHelloService() {
        if (helloService != null) {
            return helloService;
        }
        synchronized (HotDeployDemo.class) {
            if (helloService == null) {
                helloService = createHelloService();
            }
            return helloService;

        }

    }

    //具体创建方法
    private static IHelloService createHelloService() {
        try {
            MyClassLoader cl = new MyClassLoader();
            Class<?> cls = cl.loadClass(CLASS_NAME);
            if (cls != null) {
                return (IHelloService) cls.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void client() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        //一直运行
                        IHelloService helloService = getHelloService();
                        helloService.sayHello();
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    public static void monitor() {
        Thread t = new Thread() {
            private long lastModified = new File(FILE_NAME).lastModified();

            @Override
            public void run() {
                try {
                    while (true) {
                        //开启一个线程每隔100毫秒去查看文件修改时间
                        Thread.sleep(500);
                        //获取文件最后修改日期
                        long now = new File(FILE_NAME).lastModified();
                        System.out.println(now);
                        //文件最后修改日期改了
                        if (now != lastModified) {
                            //更新最后修改日期
                            lastModified = now;
                            System.out.println("xiugai");

                            //重新加载类文件
                            reloadHelloService();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    //重新加载修改后的类文件
    public static void reloadHelloService() {
        helloService = createHelloService();
    }

    public static void main(String[] args) {
        HotDeployDemo.client();
        HotDeployDemo.monitor();
    }
}
