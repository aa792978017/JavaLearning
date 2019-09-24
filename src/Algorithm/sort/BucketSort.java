package Algorithm.sort;

import java.util.Scanner;

/**
 * 桶排序
 * 思想：知道元素的范围，然后通过设置同样数量的桶，接着把元素放入对应的桶里面
 * 一般会结合散列表一起使用，在排序上使用链表等来改善他的空间利用率。类似hashMap
 * 特点：速度快，简单，但是数据跨度太大的时候空间会太大。
 * 使用场景：数据分布相对均匀或者数据跨度不大的时候使用
 */
public class BucketSort {

    public int bucket[];
    public int array[];
    public static int range = 100000;

    public BucketSort(int[] array){
        this.array = array;
        bucket = new int[range];
    }

    public void bucketSort(int[] array){
        for(int i = 0; i < array.length; i++){
            if (array != null && array.length > 1){
                bucket[array[i]]++;
            }
        }
    }

    public static void main(String arg[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++){
            array[i] = in.nextInt();
        }

        BucketSort bucketSort = new BucketSort(array);
        bucketSort.bucketSort(array);
        for(int i = 0; i < range; i++){
            System.out.println(bucketSort.bucket[i]);
        }
    }
}
