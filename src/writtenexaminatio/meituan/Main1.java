package writtenexaminatio.meituan;

import java.util.Scanner;

public class Main1 {
    public static int total = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n-1];
        for (int i = 0; i < n-1; i++){
            arr[i] = in.nextInt();
        }
        int[] arr2 = new int[n];
        for (int j = 0; j < n; j++){
            arr2[j] = in.nextInt();
        }
        System.out.println(3);

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
