package java.DesignerPattern.SingletonPattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 测试反序列化时，单例模式是否失效
 */
public class TestSerSingleton {

    public static void main(String[] args) throws Exception {

        SerSingleton serSingleton = null;
        SerSingleton serSingleton1 = null;
        serSingleton = SerSingleton.getInstance();
        FileOutputStream fos = new FileOutputStream("sersingleton.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(serSingleton);
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("sersingleton.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        serSingleton1 = (SerSingleton) ois.readObject();
        System.out.println( serSingleton.equals(serSingleton1));
        System.out.println(serSingleton + " " + serSingleton1);
    }
}
