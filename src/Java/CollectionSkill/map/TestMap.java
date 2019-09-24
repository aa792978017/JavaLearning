package Java.CollectionSkill.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    /**
     * 很hashmap功能一样，但是它是线程安全的，在一些需要同步的方法上加了synchronized关键字进行线程同步
     * 继承Dictionry，有子类Properties类
     * 不允许有null值
     */
    public Hashtable hashtable;

    /**
     * 线程不安全，允许null值，null的index都默认为0，他们hash算法，和hash值映射到索引上的算法不同
     * 可以通过Collections.synchronizedMap(hashMap)把变成线程同步的
     *
     */
    public HashMap hashMap;

    public static void main(String[] args) {
//        ConcurrentHashMap
    }
}
