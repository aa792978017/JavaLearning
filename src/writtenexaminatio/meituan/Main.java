package writtenexaminatio.meituan;

import java.util.Scanner;

public class Main {
    public static int total = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(getAns(matrix, n ,m));

    }

    private static int getAns(int[][] matrix, int n, int m) {
        int crow = 0;
        int ccol = 0;
//        while (true){
//            ccol = ccol<m?ccol:m;
//            crow = crow<n?crow:n;
//            for (int i = 0; i <= crow;i++ ){
//                for (int )
//            }
//
//
//            if (crow < n){
//                crow++;
//            }
//            if (ccol < m){
//                ccol++;
//            }
//            if (crow==n && ccol==m){
//                break;
//            }
//        }
        return n;
    }

}
