package Algorithm.offer;

/**
 * 数组中唯一只出现一次的数字
 * 在一个数组中除一个数字,只出现一次之外,其他数字都出现了三次,找出这个数字
 * 思路:位运算着手:相同的数字的1和0位置都相同,如果位按数字相加,最后一定是3的倍数,通过对这些位数取模,就能知道最后的数的二进制表示
 */
public class Main56_2 {

    public static int FindNumsAppearOnce(int[] array) {
        if (array == null || array.length == 0){
            return -1;
        }else if (array.length == 1){
            return array[0];
        }
//        思路:
//        相同的数字的1和0位置都相同,如果位按数字相加,最后一定是3的倍数,通过对这些位数取模,就能知道最后的数的二进制表示
        int[] pst = new int[32];
        for(int i = 0; i < array.length; i++){
            setMod(array,array[i],pst);
        }
        //mod3;
        int ans = 0,index = 0;

        for (int i = 0; i < 32; i++){
            ans += Math.pow(2,index++)*(pst[i]%3);
        }
        return ans;


    }

    public static void setMod(int[] arr, int ele, int[] pst){
        int i;
        int index = 0;
        while (ele != 0){
            i = ele%2;
            pst[index++] += i;
            ele >>= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,1,3,3,1,5,4,5};
        int result = FindNumsAppearOnce(arr);
        System.out.println(result);
    }


}
