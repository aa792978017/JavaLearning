package Algorithm.offer;

/**
 * 调整数组顺序使技术位于偶数之前，并且奇偶数的相对顺序不变
 * 思路：因为需要保证两边的相对顺序，最好就是整体移动的思想，
 * 找到一个在偶数后面的奇数，就让第一个偶数到其的位置后移，把这个奇数放到第一个偶数的位置上，这样就同时保证了奇偶树的相对顺序了
 *
 */
public class Main21 {
    public static void reOrderArray(int [] array) {
        if (array == null || array.length == 1 || array.length == 0){
            return;
        }
        int lo = 0;
        int lj = 0;
        int l = array.length-1;
        //从左往右找到偶数
        while (array[lo]%2 != 0){
            lo++;
        }
        lj = lo+1;
        while (true){
            //如果后面已经没有奇数了，那么排序完成
            if(lj > l){
                break;
            }
            //从第一位偶数开始往右找第一位奇数
            while ( lj <= l && array[lj]%2 != 1){
                lj++;
            }
            if(lj > l){
                break;
            }

            //如果还有奇数
            //从该偶数开始往后移动一位，把奇数放到这位偶数原来的位置
            int temp = array[lj];
            for(int i = lj; i > lo; i--){
                array[i] = array[i-1];
            }
            array[lo] = temp;
            lo++;
            lj++;

        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        reOrderArray(arr);
        System.out.println();
    }
}
