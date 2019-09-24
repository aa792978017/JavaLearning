package writtenexaminatio.t360;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main2 {


    static Map<Integer,Integer> map = new TreeMap<>(Comparator.reverseOrder());
    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int main(int n) {
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        int[] arr = new int[n];
        int max = 0;
        Iterator it = set.iterator();
        while (it.hasNext()){
            Map.Entry<Integer,Integer> e = (Map.Entry<Integer, Integer>) it.next();
            int k = e.getKey();
            int v = e.getValue();
            if (v%2 != 0){
                if (v < max){
                    return 0;
                }else{
                    max = v;

                }
            }
            arr[k] = v;
        }
        return 0;

    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x;
        int max = -1;
        for (int i = 0; i < n; i++){
             x = in.nextInt();
             if (x > max){
                 max = x;
             }
             if (map.containsKey(x)){
                 map.put(x,map.get(x)+1);
             }else{
                 map.put(x,1);
             }
        }
        System.out.println(map);
        int res;

        res = main(max);
        System.out.println(String.valueOf(res));

    }
}
