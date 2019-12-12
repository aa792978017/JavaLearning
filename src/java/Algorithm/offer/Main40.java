package java.Algorithm.offer;
import java.util.ArrayList;

/**
 * 最小的K个数，
 * 找出数组中最小的K个数
 * 实现：小顶堆，把所有元素加入小顶堆，然后弹出k个
 */
public class Main40 {
    static class Heap{
        public ArrayList<Integer> list = null;
        public Heap(int[] input){
            list = new ArrayList<>(input.length);
            for(int i = 0; i < input.length; i++){
                add(input[i]);
            }
        }
        public void add(int e){
            list.add(e);
            int cu = list.size()-1;
            while(cu != 0){
                int p = (cu-1) >> 1;
                if(p>=0){
                    if(list.get(p) > e){
                        list.set(cu,list.get(p));
                        list.set(p,e);
                        cu = p;
                    }else{
                        break;
                    }
                }
            }
        }

        public int remove(){
            if(list.size() <= 0){
                return 0;
            }
            int e = list.get(0);
            list.set(0,list.get(list.size()-1));
            list.remove(list.size()-1);
            int cu = 0;
            while(true){
                int left = (cu << 1)+1;
                int right = left + 1;
                if(left >= list.size()){
                    break;
                }
                int min = left;
                if(right < list.size() && list.get(right) < list.get(left)){
                    min = right;
                }
                if(list.get(cu) > list.get(min)){
                    int temp = list.get(cu);
                    list.set(cu,list.get(min));
                    list.set(min,temp);
                    cu = min;
                }else{
                    break;
                }
            }
            return e;
        }
    }
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k > input.length || k== 0){
            return new ArrayList<Integer>(0);
        }
        Heap h = new Heap(input);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < k ;i++){
            list.add(h.remove());
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {4,5,1,6,2,7,3,8};
        GetLeastNumbers_Solution(arr,4);
    }
}
