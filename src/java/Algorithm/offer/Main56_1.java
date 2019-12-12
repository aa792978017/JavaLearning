package java.Algorithm.offer;

/**
 *数组中数字只出现一次的两个数字
 * 通过数字在计算机里面的二进制表示,利用位运算的特点求
 */
public class Main56_1 {


        public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
            if(array == null || array.length<2){
                return;
            }

            //思路:
            //  1:第一遍遍历先找到所有数组元素的异或结果
            //  2:求出第一个位n为1的位数是第几位,并按这个位数把数组分为两个数组
            //  3:对两个数组分别异或求出两个只出现一次的整数

            //求出整个数组的异或结果
            int allResult = array[0];
            int len = array.length;
            for ( int i = 1; i < len; i++){
                allResult ^= array[i];
            }
            //算出第一位为1的位数在第几位
            int oneIndex = getOneLocation(allResult);


            int[] left = new int[len];  //放1
            int[] right = new int[len]; //放0
            int l=0,r=0;
            for(int i = 0; i < len; i++){
                if(getOneAns(array[i],oneIndex)){
                    right[r++] = array[i];
                }else{
                    left[l++] = array[i];
                }
            }
            num1[0] = left[0];
            num2[0] = right[0];
            for(int i = 1; i < l; i++){
                num1[0] ^= left[i];
            }
            for(int i = 1; i < r; i++){
                num2[0] ^= right[i];
            }




        }
        public int getOneLocation(int ele){
            int index = 0;
            while(ele != 0){
                index++;
                ele >>= 1;
            }
            return index;
        }

        public boolean getOneAns(int ele, int index){
            ele = ele >> (index-1);
            return (ele%2 == 1);
        }

}
