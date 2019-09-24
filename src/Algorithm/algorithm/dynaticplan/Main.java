package Algorithm.algorithm.dynaticplan;

import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;

/**
 * 联系动态规划
 */
public class Main {
    public static int length(char[] xArr, char[] yArr, int[][] b){
        int n = xArr.length - 1;
        int m = yArr.length - 1;
        int[][] c = new int[n+1][m+1];
        for (int i = 0; i < n;i++) c[i][0] = 0;
        for (int i = 0; i < m; i++) c[0][i] = 0;
        for (int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if (xArr[i] == yArr[j]){
                    c[i][j] = c[i-1][j-1]+1;
                    b[i][j] = 1;
                }else if (c[i-1][j] < c[i][j-1]){
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
    public static void lsc(int[][] b, int n, int m, int[] x){
        if (n >= 1 && m >= 1){
            if (b[n][m] == 1){
                x[n] = 1;
                lsc(b,n-1,m-1,x);
//                System.out.print(xArr[n]);
            }else if (b[n][m] == 2){

                lsc(b,n,m-1,x);
            }else {
                lsc(b,n-1,m,x);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String x = in.next();
        String y = in.next();
        char[] xArr = (" "+x).toCharArray();
        char[] yArr = (" "+y).toCharArray();
        int[][] b = new int[xArr.length+1][yArr.length+1];
        int l = length(xArr,yArr,b);
        int[] xx = new int[xArr.length];
        lsc(b,xArr.length-1,yArr.length-1,xx);
        for (int i = 0; i < xx.length; i++){
            if (xx[i] == 1){
                System.out.print(xArr[i]);
            }
        }
    }


}
