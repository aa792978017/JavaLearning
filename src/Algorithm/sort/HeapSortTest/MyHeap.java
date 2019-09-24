package Algorithm.sort.HeapSortTest;

import java.util.ArrayList;

/**
 * 自己实现一个小顶堆
 * 完全二叉树的性质，一层一层放，靠左边放，
 * 堆性质：父亲值总大于子元素值（大顶堆）
 *         小顶堆，类似
 */
public class MyHeap<E extends Comparable<E>> {
    // 定义一个数组作为数据结构存储堆元素
    private ArrayList<E> list ;

    //定义空参构造方法，规范
    public MyHeap(){}

    //定义构造函数，传入要排序的数组，构造堆
    public MyHeap(E[] objects){
        //根据传入数组长度实例化堆数组长度,防止扩容，加快速度
        list = new ArrayList<>(objects.length);
        for (int i = 0; i < objects.length; i++){
            //开始遍历，构造堆
            add(objects[i]);
        }

    }
    public void add(E object){
        list.add(object);
        int currentIndex = list.size() - 1;
        while (currentIndex > 0){
            int parentIndex = (currentIndex - 1)/ 2;
            if (list.get(parentIndex).compareTo(list.get(currentIndex)) > 0){
                E temp = list.get(parentIndex);
                list.set(parentIndex, list.get(currentIndex));
                list.set(currentIndex, temp);

            }else{
                break;
            }
            currentIndex = parentIndex;
        }
    }

    public E remove(){
        if (list.size() == 0){
            return null;
        }
        E removeObjectt = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int currentIndex = 0;
        while (currentIndex < list.size() - 1){
            //获取左右子树下标
            int leftChildIndex = currentIndex * 2 + 1;
            int rightChildIndex = currentIndex * 2 + 2;
            //已经没有子树了,退出
            if (leftChildIndex >= list.size()){
                break;
            }
            //有左子树，没有右子树
            if (rightChildIndex >= list.size()){
                if (list.get(leftChildIndex).compareTo(list.get(currentIndex)) < 0){
                    E temp = list.get(currentIndex);
                    list.set(currentIndex,list.get(leftChildIndex));
                    list.set(leftChildIndex, temp);
                    currentIndex = leftChildIndex;
                    break;
                }else break;
            }
            if (rightChildIndex < list.size()){
                if (list.get(leftChildIndex).compareTo(list.get(rightChildIndex)) < 0){
                    E temp = list.get(currentIndex);
                    list.set(currentIndex,list.get(leftChildIndex));
                    list.set(leftChildIndex, temp);
                    currentIndex = leftChildIndex;
                }else{
                    E temp = list.get(currentIndex);
                    list.set(currentIndex,list.get(rightChildIndex));
                    list.set(rightChildIndex, temp);
                    currentIndex = rightChildIndex;
                }
            }

        }
        return removeObjectt;
    }




}
