package Algorithm.offer;

/**
 * 股票的最大利润
 * 思想:动态规划:记录在第i处抛股的最大值,记录在i之前的最小值,然后保存每次求解的最大值
 */
public class Main63 {
    public int maxProfit(int[] prices) {
        //鲁棒性
        if (prices == null || prices.length <=0){
            return 0;
        }
        int min = prices[0];
        int len = prices.length;
        int maxAns = 0;
        int cuMax;
        for (int i = 1; i < len; i++){
            //保存最小值
            cuMax = prices[i] - min;
            maxAns = cuMax>maxAns?cuMax:maxAns;
            min = min<prices[i]?min:prices[i];
        }
        return maxAns;
    }
}