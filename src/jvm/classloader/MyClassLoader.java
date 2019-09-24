package jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{

    private String name;   //类加载器

    private String path = "/home/root-ubuntu/JAVAPRO/JvmLearning/src/jvm";  //类的加载路径

    private final String fileType = ".class";  //class 文件的扩展品

    public MyClassLoader(String name) {
        super(); //让系统类加载器成为该类的父亲加载器
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return  super.toString();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);

        //通过defineClass把字节数组转化为一个class类
        return this.defineClass(name, data, 0 ,data.length);
    }

    /**
     * 用于在计算机里面找到class文件
     * 返回字节数组,把class文件转化为字节数组的形式
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try
        {
            this.name = this.name.replace(".","\\");
            is = new FileInputStream(new File(path + name + fileType));

            baos = new ByteArrayOutputStream();

            int ch = 0;

            while(-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            try {

                is.close();
                baos.close();

            } catch(Exception e) {

                e.printStackTrace();
            }
        }
        return data;
    }

    public static void test(ClassLoader loader) throws Exception {
        Class clazz = loader.loadClass("Sample");
        Object object = clazz.newInstance();
    }

    public static void main(String[] args) throws Exception {

        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("//home/root-ubuntu//classLoaderTest//serverlib//");

        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
        loader1.setPath("//home//root-ubuntu//classLoaderTest//clientlib//");

        MyClassLoader loader3 = new MyClassLoader(null, "loader3");
        loader3.setPath("//home//root-ubuntu//classLoaderTest//otherlib//");

        test(loader2);
        test(loader3);
    }

}
