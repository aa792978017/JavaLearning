package java.Algorithm.algorithm.dynaticplan;

/**
 * 用动态规划算法解决0-1背包问题
 *
 */
public class Bag0_1 {

    public static double knapsack(double[] w, double[] v, double c, double[][] p, int[] head) {
        int n = v.length - 1;
        head[n + 1] = 0;
        p[0][0] = 0;
        p[0][1] = 0;
        int left = 0;
        int right = 0;
        int next = 1;
        head[n] = 1;
        for (int i = n; i >= 1; i--) {
            int k = left;
            for (int j = left; j <= right; j++) {
                if (p[j][0] + w[i] > c) break;
                double y = p[j][0] + w[i],
                        m = p[j][1] + v[i];
                while (k <= right && p[k][0] < y) {
                    p[next][0] = p[k][0];
                    p[next++][1] = p[k++][1];
                }
                if (k <= right && p[k][0] == y) {
                    if (m < p[k][1]) m = p[k][1];
                    k++;
                }
                if (m > p[next - 1][1]) {
                    p[next][0] = y;
                    p[next++][1] = m;
                }
                while(k <= right && p[k][1] <= p[next-1][1]) k++;
            }
            while(k <= right) {
                p[next][0] = p[k][0];
                p[next++][1] = p[k++][1];
            }
            left = right + 1;
            right = next - 1;
            head[i-1] = next;
        }
        return  p[next - 1][1];
    }

    public static void traceback(double[] w, double[] v, double[][] p, int[] head, int[] x) {
        int n = w.length - 1;
        double j = p[head[0] - 1][0],
                m = p[head[0] - 1][1];
        for (int i = 1; i <= n; i++) {
            x[i] = 0;
            for (int k = head[i+1]; k <= head[i] - 1; k++) {
                if (p[k][0] + w[i] == j && p[k][1] + v[i] == m) {
                    x[i] = 1;
                    j = p[k][0];
                    m = p[k][1];
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        int c = 10;
        int n = 5;
        double[] w = {2, 2, 6, 5, 4};
        double[] v = {6, 3, 5, 4, 6};
        double[][] p = new double[7][2];
        int[] head = new int[n+1];
        knapsack(w, v, c, p, head);
        int[] x = new int[n];
        traceback(w, v, p, head, x);
    }

    /**
     * 输入物品数量,生成物品重量数组和价值数组
     * @param n
     */
    public static void makeData(int n) {

    }


}
