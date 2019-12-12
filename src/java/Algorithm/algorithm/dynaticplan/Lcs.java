package java.Algorithm.algorithm.dynaticplan;

import java.util.Scanner;

/**
 * 最长公共子序列
 * 动态规划
 * 最优子结构
 * 重叠子结构
 */
public class Lcs {
    public static int lcsLength(char[] x, char[] y, int[][] b){
        int n = x.length-1;
        int m = y.length-1;
        int[][] c = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) c[i][0] = 0;
        for (int i = 1; i <= m; i++) c[0][i] = 0;
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                if (x[i] == y[j]){
                    c[i][j] = c[i-1][j-1]+1;
                    b[i][j] = 1;
                }else if (c[i][j-1] >= c[i-1][j]){
                    c[i][j] = c[i][j-1];
                    b[i][j] = 2;
                }else {
                    c[i][j] = c[i-1][j];
                    b[i][j] = 3;
                }
            }
        }
        return c[n][m];
    }
    public static void lcs(char[] x, int n,int m, int[][]b){

            if (n == 0 || m==0){
                return;
            }
            if (b[n][m] == 1){
                lcs(x,n-1,m-1,b);
                System.out.print(x[n]);
            }else if (b[n][m] == 2){
                lcs(x,n,m-1,b);
            }else{
                lcs(x,n-1,m,b);
            }


    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String strX = in.next();
        String strY = in.next();
        int[][] b = new int[strX.length()+1][strY.length()+1];

        lcsLength((" "+strX).toCharArray(),(" "+strY).toCharArray(),b);
        lcs((" "+strX).toCharArray(),strX.length(),strY.length(),b);
        System.out.println();


    }
}
