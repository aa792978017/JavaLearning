package Algorithm.sort;

import java.util.Random;

public class Sort {
    public int i;
    public String name;
    /**
     * 构造方法
     */
    public int[] Sort(int[] arr){
        return arr;
    }
    /**
     * 生成随机数组，传入数组个数和最大的元素值
     * @param n
     * @param maxNum
     * @return
     */
    public static int[] initArray(int n,int maxNum){
        int[] arr = new int[n];
        Random rand = new Random();
        for(int i = 0; i<n;i++){
            arr[i] = rand.nextInt(maxNum);
        }
        return arr;
    };

    /**
     * 打印排序算法说明
     * 简单重构
     * @param sortName
     * @param costTime
     * @param sumNum
     */
    public static void printTime(String sortName,long costTime,int sumNum){
        System.out.println("使用"+ sortName + "算法对长度为" + sumNum +"的数组排序，所需要花的时间为：" + costTime + "毫秒。");
    }
}
