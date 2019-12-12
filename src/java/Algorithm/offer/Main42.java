package java.Algorithm.offer;

/**
 * 连续数组的最大和
 * 思想:1:找规律 2:动态规划
 */
public class Main42 {
    /**
     * 找规律
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0){
            return 0;
        }else if(array.length == 1){
            return array[0];
        }else {
            int len = array.length;
            int maxSum = array[0];
            int sum = array[0];
            for(int i = 1; i < len; i++){

                if(sum <= 0){
                    sum = array[i];
                }else{
                    sum += array[i];
                }
                //是否为当前的最大值
                if(sum > maxSum){
                    maxSum = sum;
                }

            }
            return maxSum;
        }
    }

    /**
     * 使用动态规划
     * F（i）：以array[i]为末尾元素的子数组的和的最大值，子数组的元素的相对位置不变
     * F（i）=max（F（i-1）+array[i] ， array[i]）
     * res：所有子数组的和的最大值
     * res=max（res，F（i））
     * @param array
     * @return
     */
    public  int FindGreatestSumOfSubArray1(int[] array) {
        int res = array[0]; //记录当前所有子数组的和的最大值
        int max=array[0];   //包含array[i]的连续数组最大值
        for (int i = 1; i < array.length; i++) {
            max=Math.max(max+array[i], array[i]);
            res=Math.max(max, res);
        }
        return res;
    }


}