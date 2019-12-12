package java.Algorithm.leetcode.dp;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 思路:
 * # 思路：
 *
 * 动态规划：通过把原问题分解为相对简单的子问题的方式求解复杂问题的方法。动态规划常常适用于有重叠子问题和最优子结构性质的问题。
 *
 * 从题目给出例题输入来找规律，我们需要一个head来指向最优解序列的头结点，head指向将不断变化，定义一个最优解数组dp[i]记录截至到当前元素的最优子序和.
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 *
 * 输出: 6
 *
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 初始化dp[0] = -2
 *
 * -2 : dp[0] = -2, head = 0
 *
 * 1 : dp[1] = 1 head = 1
 *
 * -3 : dp[2] = -2 head = 1
 *
 * 通过前三项可以总结出规律：dp[i] = max(dp[i-1] + nums[i], nums[i]) 如果选中当前项，则更改head指向当前项
 *
 * 4 : dp[3] = max(dp[2] + 4, 4) = max(-2, 4) = 4 head = 3
 *
 * -1: dp[4] = max(dp[3] +-1 , -1) = max(3, -1) = 3 head不变
 *
 * 2： dp[5] = max(5, 2) = 5, head 不变
 *
 * 1 : dp[6] = 6 head不变
 *
 * -5 ： dp[7] = 1 head不变
 *
 * 4 : dp[8] = 5 head不变
 *
 * 我们只需要其中最大的子序号，遍历一遍dp数组找出最大项dp[i]，并且从 head 到 i 的序列就是最大子序列
 * 作者：hikes
 * 链接：https://leetcode-cn.com/problems/two-sum/solution/dong-tai-gui-hua-qiu-jie-zui-da-zi-xu-he-by-hikes/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Main53 {
    public static int maxSubArray(int[] nums) {
        if(nums == null){
            return -1;
        }
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }
        int max = nums[0];
        int count = nums[0];
        for(int i = 1; i < len; i++){
            count = (count+nums[i]) < nums[i]?nums[i]:(count+nums[i]);
            max = count>max?count:max;
        }

        return max;
    }
}