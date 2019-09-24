package Algorithm.offer;

import java.util.ArrayList;

/**
 * 和为s的数字，如果有多对，输出两数乘积最小的那对
 * 数组两边收缩来求
 */
public class Main57 {

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        int j = array.length-1;
        int i = 0;
        //定义初始最小值
        int min = Integer.MAX_VALUE;
        //和为s，且乘积最小的那一对
        ArrayList<Integer> list = new ArrayList<>(2);

        //从两边往中间遍历
        while(i<j){
            if(array[i]+array[j] < sum){
                //偏小，左边界收缩
                i++;
            }else if(array[i]+array[j] > sum){
                //偏大，右边界收缩
                j--;
            }else {
                int mut = array[i]*array[j];
                //判断是否是积最小，是的话替换之前的
                if(min > mut){
                    min = mut;
                    //如果不是第一组和为s的数对
                    if(list.size() >= 2){
                        list.set(0,array[i]);
                        list.set(1,array[j]);
                    }else{
                        //如果是第一组和为s的数对
                        list.add(array[i]);
                        list.add(array[j]);
                    }
                }
                //找到一组后，两个边界都要收缩
                i++;
                j--;
            }
        }

        return list;

    }
}
