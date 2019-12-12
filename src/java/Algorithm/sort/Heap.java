package java.Algorithm.sort;

import java.util.ArrayList;

/**
 * 用数组实现一个大顶堆,小顶堆类似
 * 时间复杂度：O(nlogn)
 * 空间复杂度比归并排序要好
 * @param <E>
 */
public class Heap<E extends Comparable<E>>{
    //用于建立堆的数组
    private ArrayList<E> list = new ArrayList<>();
    //空参构造方法,规范
    public Heap(){}
    //传入需要排序的数组,进行堆的构造
    public Heap(E[] objects){
        //直接定义数组最终大小,防止扩容带来的额外时间消耗
        list = new ArrayList<>(objects.length);
        //遍历要排序的数组,依次存入堆中,进行排序
        for (int i = 0; i < objects.length; i++){
            add(objects[i]);
        }
    }

    /**
     * 添加元素的时候,先添加到数组最后,然后不断跟父亲比较,
     * 若当前元素比父亲大则与父亲交换位置,直至父亲元素大于当前元素值
     * @param newObject
     */
    public void add(E newObject){
        //把元素添加到数组末尾
        list.add(newObject);
        //获取当前元素添加进去的位置
        int currentIndex = list.size() - 1;
        //不断与父亲比较,比父亲大则交换
        while (currentIndex > 0){
            //获取父亲下标
            int parentIndex = (currentIndex - 1) / 2;
            //比父亲大,交换
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }else{
                //比父亲小,退出
                break;
            }
            currentIndex = parentIndex;
        }
    }

    /**
     * 把最后一个元素替换到堆顶，然后跟左右子元素比较获取最大那个,替换位置
     * 直到左右子元素都比当前元素小
     * @return
     */
    public E remove(){
        //堆是空的,返回
        if (list.size() == 0){
            return null;
        }
        //获取堆顶元素返回
        E removiedObject = list.get(0);
        //把最后一个元素放到堆顶,然后删除最后一个元素
        list.set(0,list.get(list.size() - 1));
        list.remove(list.size() - 1);
        //从堆顶依次往下比较,若子元素比它大,交换位置,直到没有子元素或者子元素的值比当前元素值小
        int currentIndex = 0;
        while (currentIndex < list.size()){
            //获取左右子元素下标
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            //当左右子元素都不存在,退出循环
            if (leftChildIndex >= list.size()) {
                break;
            }
            //当存在子元素
            int maxIndex = leftChildIndex;
            //若右子元素也存在,获取子元素中最大值的下标
            if (rightChildIndex < list.size()){
                if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0){
                    maxIndex = rightChildIndex;
                }
            }
            //比较子元素最大值和当前值的大小,如果子元素大,则交换位置
            if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0){
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }else{
                //子元素已经都比当前元素小,退出循环
                break;
            }

        }
        return removiedObject;

    }

    public int getSize(){
        return list.size();
    }
}
