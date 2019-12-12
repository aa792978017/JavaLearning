package java.Algorithm.algorithm.dynaticplan;

import java.util.Date;
import java.util.Random;

/**
 * 最长公共子序列问题
 * 输入：两个序列
 * 输入：最长的公共子序列长度以及最长的公共子序列
 */
public class Ics {
    /**
     * 获取最优解：最长公共子序列的长度
     * @param x
     * @param y
     * @param b
     * @return
     */

    public static int LscLength(char[] x, char[] y, int[][] b) {
        int m = x.length - 1;
        int n = y.length - 1;
        int[][] c = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            c[i][0] = 0;
        for (int i = 1; i <= n; i++)
            c[0][i] = 0;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                } else if (c[i][j - 1] > c[i - 1][j]) {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 3;
                }
            }
        return c[m][n];

    }

    /**
     * 获取最长公共子序列的序列
     *
     * @param i
     * @param j
     * @param x
     * @param b
     */
    public static void lcs(int i, int j, char[] x, int[][] b) {
        if (i == 0 || j == 0)
            return;
        if (b[i][j] == 1) {
            lcs(i - 1, j - 1, x, b);
            System.out.print(x[i] + " ");
        } else if (b[i][j] == 2) {
            lcs(i, j - 1, x, b);
        } else {
            lcs(i - 1, j, x, b);
        }
    }

    public static void main(String[] args) {

        //使用自定义的makeData函数生成随机序列x和y
        int n = 4000;
        int m = 3000;
        System.out.println("序列X和Y的长度分别为:" + n + "和" + m);
        char[] x = makeData(n);
        char[] y = makeData(m);
//        初始化二维数组b
        int[][] b = new int[x.length][y.length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                b[i][j] = 0;
            }
        }
        Date beginTime = new Date();
        //求出最长公共子序列长度
        int sum = LscLength(x, y, b);
        Date endTime = new Date();
        System.out.println("LscLength耗时" +
                (int) (endTime.getTime() - beginTime.getTime()) + "毫秒");
        //输出最长公共自序列
        System.out.println("最长公共子序列为:");
        lcs(x.length - 1, y.length - 1, x, b);
        beginTime = new Date();
        System.out.println();
        System.out.println("lsc算法耗时:" + (int) (beginTime.getTime() - endTime.getTime()) + "毫秒");

        System.out.println("最长公共自序了长度为:" + sum);
    }

    /**
     * 用于生成序列X和Y
     * 输入n,生成一个长度为n的,元素在a-z的之间的序列
     *
     * @param n
     * @return
     */
    public static char[] makeData(int n) {
        char[] x = new char[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            x[i] = (char) (random.nextInt(26) + 97);
        }
        return x;

    }
}



