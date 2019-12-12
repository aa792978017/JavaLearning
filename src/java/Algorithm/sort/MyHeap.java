package java.Algorithm.sort;

import java.util.ArrayList;

/**
 * 大顶堆
 */
public class MyHeap {
    private static ArrayList<Integer> list;
    public MyHeap(int[] arr){
        list = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++){
            add(arr[i]);

        }
    }

    private void add(int i) {
        list.add(i);
        int current = list.size()-1;
        while (current > 0){
            int parent = ((current-1)/2);
            if (parent < list.size()-1 && list.get(parent) < list.get(current)){

                    int temp = list.get(parent);
                    list.set(parent,list.get(current));
                    list.set(current,temp);


            }else {
                break;
            }
            current = parent;
        }
    }
    public int remove(){
       int e = list.get(0);
       list.set(0,list.get(list.size()-1));
       list.remove(list.size()-1);
       int current = 0;
       while (true){
           int left = (current<<1) + 1;
           int right = (current<<1) + 2;
           if (left >= list.size()){
               return e;
           }else{
               int maxIndex = left;
               if (right < list.size() && list.get(left) <= list.get(right)){
                   maxIndex = right;
               }
               if (list.get(maxIndex) > list.get(current)){
                   int temp = list.get(current);
                   list.set(current,list.get(maxIndex));
                   list.set(maxIndex,temp);
                   current = maxIndex;
               }else{
                   return e;
               }
           }
       }
    }

}
