package Algorithm.leetcode;

/**
 * 整数划分问题
 * 动态规划，
 * 要分析清楚，
 * 几个特点：1,求最优值，2,最优子结构，3,重叠子问题
 * 子问题的答案先存起来
 */
public class Main343 {
    public int integerBreak(int n) {

        if (n == 2){
            return 1;
        }else if (n == 3){
            return 2;
        }
        int[] max = new int[n+1];
        max[1] = 1;
        max[2] = 2;
        max[3] = 3;
        int ans = 0;
        for (int i = 4; i <= n; i++){
            ans = 0;
            for (int j = 1; j <= i/2 + 1; j++){
                int cur = max[j] * max[i-j];
                if (ans < cur){
                    ans = cur;
                    max[i] = ans;
                }

            }
        }
        ans = max[n];
        return ans;

    }



    public static void main(String[] args) {
        Main343 main343 = new Main343();
        main343.integerBreak(10);
    }
}
