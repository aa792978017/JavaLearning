package java.Algorithm.offer;
import java.util.ArrayList;

/**
 * 数据流中的中位数
 * 思想:通过构建最大堆存前一半数,构建最小堆存后一半数,来降低时间复杂度
 * 分析:用排序等都可以做,但是如果将存和取的时间复杂度降低是关键,这里用AVL效果也一样,但是在面试里面很难快速写出AVL数
 */
public class Main41 {
    MyHeapMin min = new MyHeapMin();
    MyHeapMax max = new MyHeapMax();
    int count = 0;

    public void Insert(Integer num) {
        count++;
        //偶数插入最小堆
        //如果该数小于最大堆最小值,插入最大堆
        if((count&1) == 0){
            if(max.getSize() > 0 && max.getTop() > num){
                max.add(num);
                num = max.remove();
            }
            min.add(num);
        }else{
            //奇数插入最大堆
            if(min.getSize() > 0 && min.getTop() < num){
                min.add(num);
                num = min.remove();
            }
            max.add(num);
        }

    }

    public Double GetMedian() {
        if(count == 0){
            return 0.0;
        }
        if((count&1) == 0){
            return (double)(min.getTop() + max.getTop()) / 2;

        }else{
            return (double)max.getTop();
        }
    }

    class MyHeapMax {
        public  ArrayList<Integer> list = new ArrayList<>();
        public MyHeapMax(int[] arr){
            list = new ArrayList<>(arr.length);
            for (int i = 0; i < arr.length; i++){
                add(arr[i]);
            }
        }

        public int getSize(){
            return list.size();
        }

        public int getTop(){
            return list.get(0);
        }


        public MyHeapMax(){}

        public void add(int i) {
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
    class MyHeapMin {
        public ArrayList<Integer> list = new ArrayList<>();

        public int getSize(){
            return list.size();
        }

        public int getTop(){
            return list.get(0);
        }
        public MyHeapMin(){}

        public void add(int i) {
            list.add(i);
            int current = list.size()-1;
            while (current > 0){
                int parent = ((current-1)/2);
                if (parent < list.size()-1 && list.get(parent) > list.get(current)){

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
                    int minIndex = left;
                    if (right < list.size() && list.get(left) > list.get(right)){
                        minIndex = right;
                    }
                    if (list.get(minIndex) < list.get(current)){
                        int temp = list.get(current);
                        list.set(current,list.get(minIndex));
                        list.set(minIndex,temp);
                        current = minIndex;
                    }else{
                        return e;
                    }
                }
            }
        }

    }


}