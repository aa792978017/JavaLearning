package java.Algorithm.sort;

import java.util.Date;
import java.util.Scanner;

/**
 * 合并排序
 * 算法思想：
 *用分治的思想，不断把原来的数组分割为原来的二分之一，直至只剩下一个元素，即为已经排好序的单元素数组，然后开始合并排序，最后形成排好序的数组
 * 算法复杂度为：O（nlogn）
 */
public class MergeSort extends Sort{



    //归并排序的入口，这个算法体主要进行数组拆分和调用合并函数，进而完成整个合并排序算法。
    public static void mergeSort(int arr[]){
        if(arr.length > 1){
            int[] firstHalf = new int[arr.length/2];
            System.arraycopy(arr,0,firstHalf,0,arr.length/2);
            mergeSort(firstHalf);
            int[] secondHalf = new int[arr.length - firstHalf.length];
            System.arraycopy(arr,arr.length/2,secondHalf,0,secondHalf.length);
            mergeSort(secondHalf);
            merge(firstHalf,secondHalf,arr);
        }


    }
    //合并函数，把拆分的数组合并起来
    public static void merge(int[] firstHalf,int[] secondHalf,int[] temp){
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;
        while(current1 < firstHalf.length && current2 < secondHalf.length){
            if (firstHalf[current1] < secondHalf[current2]){
                temp[current3++] = firstHalf[current1++];
            }else{
                temp[current3++] = secondHalf[current2++];
            }
        }
        while(current1 < firstHalf.length){
            temp[current3++] = firstHalf[current1++];
        }
        while(current2 < secondHalf.length){
            temp[current3++]  = secondHalf[current2++];
        }
    }

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("请分别输入要生成的随机数组的长度和元素最大值：");
        int sortNum = input.nextInt();              //设定要随机生成数组的长度
        int MaxNum = input.nextInt();                 //设定随机数组元素的最大值
        int[] arr = Sort.initArray(sortNum, MaxNum); //调用生成随机数组的函数
        System.out.println("元素个数：" + sortNum);
        Date beginTime = new Date();
        System.out.println("归并排序开始时间：" + beginTime);
        mergeSort(arr);
        Date endTime = new Date();
        System.out.println("归并排序结束时间：" + beginTime);
        long costTime = endTime.getTime() - beginTime.getTime();   //计算输出排序所用的时间
        printTime("MergeSort",costTime,arr.length);
    }
}
