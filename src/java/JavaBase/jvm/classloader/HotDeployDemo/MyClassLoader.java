package java.JavaBase.jvm.classloader.HotDeployDemo;

import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {
    private static final String BASE_DIR = "/home/voidcc/桌面/JavaLearning/src/Java.jvm/classloader/HotDeployDemo";


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.replace("\\.","/");
        fileName = BASE_DIR + fileName + ".class";
        System.out.println(fileName);
        try {
            byte[] bytes = new byte[1024];
//            byte[] bytes1 = ByteArrayInputStream()
            FileInputStream x = new FileInputStream(fileName);
            x.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return null;
        }


    }
}
