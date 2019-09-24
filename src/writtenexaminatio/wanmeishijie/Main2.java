package writtenexaminatio.wanmeishijie;
import writtenexaminatio.zhaoshang.Main;

import java.util.Random;
import java.util.Scanner;

public class Main2 {

    public static void knapsack(int[] v, int[] w, int c, int[][] m) {
        int n = v.length - 1;
        int jMax = Math.min(w[n]-1, c);
        for (int j = 0; j <=jMax; j++){
           m[n][j] = 0;
        }
        for (int j = w[n]; j <= c; j++){
            m[n][j] = v[n];
        }
        for (int i = n-1; i > 1; i--){
            jMax = Math.min(w[i]-1,c);
            for (int j = 0; j <= jMax; j++){
                m[i][j] = m[i+1][j];
            }
            for (int j = w[i]; j <= c; j++){
                m[i][j] = Math.max(m[i+1][j],m[i+1][j-w[i]]+v[i]);
            }
        }
        m[1][c] = m[2][c];
        if (c >= w[1]){
            m[1][c] = Math.max(m[1][c],m[2][c-w[1]]+v[1]);
        }

    }

        public static void traceback(int[] w, int c, int[][] m, int[] x) {
            int n = w.length - 1;
            for (int i = 1; i<n;i++){
                if(m[i][c] == m[i+1][c]) {
                    x[i]=0;
                } else{
                    x[i] = 1;
                    c-=w[i];
                }
            }
            x[n] = (m[n][c]>=0)? 1 :0;
        }

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            int c;
            int n = in.nextInt();
            int[] w = new int[n];
            int[] v = new int[n];
//            for (int i = 0; i < n; i++){
//                v[i] = in.nextInt();
//            }
//            for (int i = 0; i < n; i++){
//                w[i] = in.nextInt();
//            }
            w = makeData(n,100);
            v = makeData(n,100);
//            c = in.nextInt();
            c = 100;
            int[][] m = new int[n+2][c+2];
            knapsack(v,w,c,m);
            int[] x = new int[n];
            traceback(w,c,m,x);
            int sum = 0;
            for (int i = 0; i < n; i++){
                if (x[i] == 1){
                    sum += v[i];
                }
            }
            System.out.println(sum);
        }
    /**
     * 输入物品数量,和最大值,生成物品重量数组和价值数组
     * @param n
     */
    public static int[] makeData(int n, int maxValue) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = (int) random.nextInt(maxValue);
        }
        return arr;
    }



    }


