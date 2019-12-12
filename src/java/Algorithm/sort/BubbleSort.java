package java.Algorithm.sort;

/**
 * 冒泡排序
 * 思想：每次遍历比较大小，然后比较前后两个值大小， 先找到最大或者最小的元素，放到数组首端或者尾端，通过不断比较，不断交换位置来实现
 *
 * 时间复杂度：n方
 */
public class BubbleSort {
    /**
     * 增加一个标志位来优化+一次冒泡两个元素（一个最大值，一个最小值）
     * @param array
     */
    public static void bubbleSort(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }
        int maxIndexValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length - i; j++) {
                //每次循环找出一个最小值，不断交换
                if (array[j] < array[i]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
                //每次循环找出一个最大值
                    if (array[j] > maxIndexValue){
                        maxIndex = j;
                        maxIndexValue = array[j];}
                }
            //把最大值放到最后
                array[maxIndex] = array[array.length - i - 1];
                array[array.length - i - 1] = maxIndexValue;
                maxIndexValue = array[0];
                maxIndex = 0 ;
            }
        }

    public static void print(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i] + "  ");
        }
    }

    public static void main(String[] args) {
        int[] array = {11,123,35,62,13,67,24};
        BubbleSort.bubbleSort(array);
        BubbleSort.print(array);
    }

}
