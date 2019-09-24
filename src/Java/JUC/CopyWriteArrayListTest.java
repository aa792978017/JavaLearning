package Java.JUC;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyWriteArrayListTest {
    private static volatile CopyOnWriteArrayList<String> arrayList = new
            CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        arrayList.add("hello");
        arrayList.add("alibaba");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("hangzhou");

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                arrayList.set(1,"baba");
                arrayList.remove(2);
                arrayList.remove(3);
            }
        });

        Iterator<String> it = arrayList.iterator();
        one.start();
        //one.join();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
