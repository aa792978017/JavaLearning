package Algorithm.sort.HeapSortTest;

import jvm.gc.StopWorldTest;

import java.util.ArrayList;

public class MyHeap1<E extends Comparable<E>> {

    public ArrayList<E> list;
    public MyHeap1(){}
    public MyHeap1(E[] objects){
        list = new ArrayList<>(objects.length);
        for (int i = 0; i < objects.length; i++){
            add(objects[i]);
        }
    }

    public void add(E object){
        list.add(object);
        int currentIndex = list.size() - 1;
        if (currentIndex == 0){
            return;
        }
        while (currentIndex > 0){
            int parentIndex = (currentIndex - 1) >> 1;
            if (list.get(parentIndex).compareTo(list.get(currentIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
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
        E removeObject = list.get(0);
        list.set(0,list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int currentIndex = 0;
        while (currentIndex < list.size()){
            int leftChildIndex = (currentIndex << 1) + 1;
            int rightChildIndex = (currentIndex << 1) + 2;
            if (leftChildIndex >= list.size()){
                break;
            }
            int minIndex = leftChildIndex;
            if (rightChildIndex < list.size()){
                if (list.get(leftChildIndex).compareTo(list.get(rightChildIndex)) > 0){
                    minIndex = rightChildIndex;
                }
            }

            if (list.get(currentIndex).compareTo(list.get(minIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(minIndex));
                list.set(minIndex, temp);
                currentIndex = minIndex;
            }else{
                break;
            }
        }

        return removeObject;
    }
}
