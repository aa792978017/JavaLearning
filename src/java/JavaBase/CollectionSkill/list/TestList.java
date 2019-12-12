package java.JavaBase.CollectionSkill.list;

public class TestList {
    /**
     * ArraryList 和 Vector几乎使用了相同的算法，都是使用数组实现的。
     * 后者是线程安全的
     */

    /**
     * List的add()性能
     * ArrayList的add性能主要取决于ensureCapacity，而且如果初始容量很大的话，效率会高很多，减少很多扩容时的时间损耗
     */

    /**
     * 性能测试技巧：
     * 1.通过多次执行相同的操作让运行时间变得明显
     * 2.测试的时候最好屏蔽GC的影响：通过设置：-Xmx512M -Xms512M来扩大对内存，减少gc
     */

    /**
     * 如果系统应用每次经常需要在任意位置插入元素用LinkedList会比较好
     * 需要随机访问的时候使用ArrayList比较好
     */

    /**
     * 在遍历List的时候 速度排序为：for循环>迭代器>foreach
     */

}
